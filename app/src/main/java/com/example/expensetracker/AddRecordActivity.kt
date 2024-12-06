package com.example.expensetracker

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.expensetracker.databinding.ActivityAddRecordBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class AddRecordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddRecordBinding
    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveButton.setOnClickListener {
            val amount = binding.amountEditText.text.toString().toDoubleOrNull()
            val category = binding.categorySpinner.selectedItem.toString()
            val description = binding.descriptionEditText.text.toString().trim()

            if (amount == null || amount <= 0) {
                Toast.makeText(this, "Please enter a valid amount", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (description.isEmpty()) {
                Toast.makeText(this, "Please enter a description", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val userId = auth.currentUser?.uid ?: return@setOnClickListener

            val record = hashMapOf(
                "amount" to amount,
                "category" to category,
                "description" to description,
            )

            db.collection("users").document(userId).collection("expenses")
                .add(record)
                .addOnSuccessListener {
                    Toast.makeText(this, "Added successfully!", Toast.LENGTH_SHORT).show()
                    finish()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Failed to add", Toast.LENGTH_SHORT).show()
                }
        }
    }
}
