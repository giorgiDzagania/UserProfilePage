package com.exercise.homeworkthree

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.exercise.homeworkthree.databinding.ActivityMainBinding
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @SuppressLint("SetTextI18n")
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
                Toast.makeText(this, R.string.invalid_email_message, Toast.LENGTH_SHORT).show()
            }else if (!isValidMinUserNameNumber(userName)){
                Toast.makeText(this, R.string.invalid_username_message, Toast.LENGTH_SHORT).show()
            }else if (fName.isEmpty()){
                Toast.makeText(this, R.string.empty_first_name_message, Toast.LENGTH_SHORT).show()
            }else if (lName.isEmpty()){
                Toast.makeText(this, R.string.empty_last_name_message, Toast.LENGTH_SHORT).show()
            }else if (!isNotValidAge(userAge) || userAge.isEmpty()){
                Toast.makeText(this, R.string.invalid_age_message, Toast.LENGTH_SHORT).show()
            }
            else if (isValidGmailAddress(gmail) && isValidMinUserNameNumber(userName)
                && fName.isNotEmpty() && lName.isNotEmpty() && userAge.isNotEmpty()){
                profileResult()
                binding.tvGmailResult.text = gmail
                binding.tvUserNameResult.text = userName
                binding.tvFullNameResult.text = "$fName $lName"
                binding.tvAgeResult.text = userAge
            }else{
                Toast.makeText(this, R.string.please_provide_information, Toast.LENGTH_SHORT).show()
            }
        }

        // Button short Click shows Toast
        binding.btnClear.setOnClickListener {
            val message = R.string.short_click_message
            Toast.makeText(this@MainActivity,message, Toast.LENGTH_SHORT).show()
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
        val validPattern = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\$")
        val match = validPattern.matcher(gmail)
        return match.matches()
    }

    private fun isValidMinUserNameNumber(userNumber:String):Boolean {
        val validNumber = 10
        val number = userNumber.length
        return number >= validNumber
    }

    private fun isNotValidAge(userAge: String):Boolean {
        return userAge != "0"
    }

}