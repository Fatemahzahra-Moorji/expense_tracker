package com.example.expensetracker

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.expensetracker.databinding.ActivityRegisterPageBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class RegisterPage : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterPageBinding
    private lateinit var auth: FirebaseAuth
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.registerButton.setOnClickListener {
            val name = binding.nameEditText.text.toString().trim()
            val email = binding.emailEditText.text.toString().trim()
            val password = binding.passwordEditText.text.toString().trim()
            val confirmPassword = binding.confirmPasswordEditText.text.toString().trim()

            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password != confirmPassword) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val userId = auth.currentUser?.uid ?: return@addOnCompleteListener

                        // Save user details to Firestore
                        val user = hashMapOf(
                            "name" to name,
                            "email" to email
                        )

                        db.collection("users").document(userId)
                            .set(user)
                            .addOnSuccessListener {
                                Toast.makeText(this, "Registration successful!", Toast.LENGTH_SHORT).show()
                                startActivity(Intent(this, LoginPage::class.java))
                                finish()
                            }
                            .addOnFailureListener { exception ->
                                Toast.makeText(this, "Failed to save user data: ${exception.message}", Toast.LENGTH_SHORT).show()
                            }
                    } else {
                        Toast.makeText(this, "Registration failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
        }

        binding.loginTextView.setOnClickListener {
            startActivity(Intent(this, LoginPage::class.java))
        }
    }
}
