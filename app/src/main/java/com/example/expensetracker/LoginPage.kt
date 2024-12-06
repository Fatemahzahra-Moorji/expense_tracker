package com.example.expensetracker

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.expensetracker.databinding.ActivityLoginPageBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class LoginPage : AppCompatActivity() {

    private lateinit var binding: ActivityLoginPageBinding
    private lateinit var auth: FirebaseAuth
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.loginButton.setOnClickListener {
            val email = binding.emailEditText.text.toString().trim()
            val password = binding.passwordEditText.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val userId = auth.currentUser?.uid ?: return@addOnCompleteListener

                        // Fetch user data
                        db.collection("users").document(userId)
                            .get()
                            .addOnSuccessListener { document ->
                                val name = document.getString("name")
                                Toast.makeText(this, "Welcome back, $name!", Toast.LENGTH_SHORT).show()
                                startActivity(Intent(this, MainActivity::class.java))
                                finish()
                            }
                            .addOnFailureListener {
                                Toast.makeText(this, "Login successful, but failed to fetch user data.", Toast.LENGTH_SHORT).show()
                                startActivity(Intent(this, MainActivity::class.java))
                                finish()
                            }
                    } else {
                        Toast.makeText(this, "Login failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
        }

        binding.registerTextView.setOnClickListener {
            startActivity(Intent(this, RegisterPage::class.java))
        }
    }
}
