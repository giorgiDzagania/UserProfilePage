package com.exercise.homeworkthree

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.exercise.homeworkthree.databinding.ActivityMainBinding
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        profileInfoMain()

        binding.btnSave.setOnClickListener {
            val gmail = binding.etGmail.text.toString()
            val userName = binding.etUserName.text.toString()
            val fName = binding.etFirstName.text.toString()
            val lName = binding.etLastName.text.toString()
            val userAge = binding.etAge.text.toString()

            if (!isValidGmailAddress(gmail)) {
                Toast.makeText(this, "გთხოვთ შეიყვანოთ სწორი მეილი", Toast.LENGTH_SHORT).show()
            }else if (!isValidMinUserNameNumber(userName)){
                Toast.makeText(this, "'Username'-ში სიმბოლოების რაოდენობა მინიმუმ 10", Toast.LENGTH_LONG).show()
            }else if (fName.isEmpty()){
                Toast.makeText(this, "გთხოვთ შეიყვანოთ სახელი", Toast.LENGTH_SHORT).show()
            }else if (lName.isEmpty()){
                Toast.makeText(this, "გთხოვთ შეიყვანოთ გვარი", Toast.LENGTH_SHORT).show()
            }else if (userAge.isEmpty()){
                Toast.makeText(this, "გთხოვთ შეიყვანოთ ასაკი", Toast.LENGTH_SHORT).show()
            }
            else if (isValidGmailAddress(gmail) && isValidMinUserNameNumber(userName)
                && fName.isNotEmpty() && lName.isNotEmpty() && userAge.isNotEmpty()){
                //TODO impliment next page
                profileResult()
                binding.tvGmailResult.text = gmail
                binding.tvUserNameResult.text = userName
                binding.tvFullNameResult.text = "$fName $lName"
                binding.tvAgeResult.text = userAge
            }else{
                Toast.makeText(this, "გთხოვთ შეავსოთ ინფორმაცია", Toast.LENGTH_SHORT).show()
            }
        }

        // Button short Click shows Toast
        binding.btnClear.setOnClickListener {
            Toast.makeText(this@MainActivity,
                "თუ გინდათ მონაცემების ხელახლა შეყვანა დააჭირეთ დიდხანს cancel ღილაკს", Toast.LENGTH_SHORT).show()
        }

        // Button on Long Click Clears All Fields
        binding.btnClear.setOnLongClickListener {
            binding.etGmail.text.clear()
            binding.etUserName.text.clear()
            binding.etFirstName.text.clear()
            binding.etLastName.text.clear()
            binding.etAge.text.clear()
            return@setOnLongClickListener true
        }

        binding.btnAgain.setOnClickListener {
            profileInfoMain()
        }

    }


    private fun profileInfoMain(){
        binding.linearLayoutMainPage.visibility = View.VISIBLE
        binding.linearLayoutResult.visibility = View.GONE

        binding.etGmail.text.clear()
        binding.etUserName.text.clear()
        binding.etFirstName.text.clear()
        binding.etLastName.text.clear()
        binding.etAge.text.clear()
    }

    private fun profileResult(){
        binding.linearLayoutResult.visibility = View.VISIBLE
        binding.linearLayoutMainPage.visibility = View.GONE
    }


    private fun isValidGmailAddress(gmail: String): Boolean {
        val validPattern = Pattern.compile("^[A-Za-z0-9]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}\$")
        val match = validPattern.matcher(gmail)
        return match.matches()
    }

    private fun isValidMinUserNameNumber(userNumber:String):Boolean {
        var validNumber = 10
        var userNumber = userNumber.length
        return userNumber >= validNumber
    }

}