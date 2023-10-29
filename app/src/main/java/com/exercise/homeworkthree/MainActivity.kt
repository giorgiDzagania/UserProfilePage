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
                toast(getString(R.string.invalid_email_message))
            }else if (!isValidMinUserNameNumber(userName)){
                toast(getString(R.string.invalid_username_message))
            }else if (fName.isEmpty()){
                toast(getString(R.string.empty_first_name_message))
            }else if (lName.isEmpty()){
                toast(getString(R.string.empty_last_name_message))
            }else if (!isNotValidAge(userAge) || userAge.isEmpty()){
                toast(getString(R.string.invalid_age_message))
            }
            else if (isValidGmailAddress(gmail) && isValidMinUserNameNumber(userName)
                && fName.isNotEmpty() && lName.isNotEmpty() && userAge.isNotEmpty()){
                profileResult()
                binding.tvGmailResult.text = gmail
                binding.tvUserNameResult.text = userName
                binding.tvFullNameResult.text = getString(R.string.full_name_result,fName,lName)
                binding.tvAgeResult.text = userAge
            }else{
                toast(getString(R.string.please_provide_information))
            }
        }

        // Button short Click shows Toast
        binding.btnClear.setOnClickListener { toast(getString(R.string.short_click_message)) }

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

    private fun toast(text: String){
        Toast.makeText(this@MainActivity,
            text, Toast.LENGTH_SHORT).show()
    }

    private fun profileInfoMain(){
        binding.linearLayoutMainPage.visibility = View.VISIBLE
        binding.linearLayoutResult.visibility = View.GONE

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