package com.example.expensetracker

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.expensetracker.databinding.ActivityRegisterPageBinding

class RegisterPage : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityRegisterPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        val users=emptyMap<String, String>()
        binding.registerSubmitButton.setOnClickListener(){
                //grab what user submitted to edittext and save to variable


            val i= Intent(this,MainActivity::class.java)
            i.putExtra("username","hei")
            i.putExtra("password","hei")
            startActivity(i)

        }
    }
}