package com.example.expensetracker

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.expensetracker.databinding.ActivityStartPageBinding

class StartPage : AppCompatActivity() {
    private lateinit var binding: ActivityStartPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.loginButton.setOnClickListener(){
        //adding intent calling login activity so user can log into their account
            val i = Intent(this, LoginPage::class.java)
            startActivity(i)
        }

        binding.registerButton.setOnClickListener(){
            //add intent calling the register activity so user can register for an account
            val i = Intent(this, RegisterPage::class.java)
            startActivity(i)
        }


    }
}