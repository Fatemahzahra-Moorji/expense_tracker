package com.example.expensetracker

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.expensetracker.databinding.ActivityLoginPageBinding

class LoginPage : AppCompatActivity() {
    private lateinit var binding : ActivityLoginPageBinding
    lateinit var usernameInput: EditText
    lateinit var passwordInput: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()

        binding.loginButton.setOnClickListener(){
            //grab what user submitted to edittext and save to variable
            usernameInput=binding.usernameTxt
            passwordInput=binding.passwordTxt

            //login page goes to the main page once user clicks on the Login button
            val i = Intent(this,MainActivity::class.java)

            //reference the variables from fatemahzahra's page that connects this page to the database
            //testing
            i.putExtra("usernameInput",0)
            i.putExtra("passwordInput",0)
            startActivity(i)
        }
    }
}