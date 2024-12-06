package com.example.expensetracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.expensetracker.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: BudgetViewModel by viewModels()
    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = BudgetAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        viewModel.expenses.observe(this) { expenses ->
            adapter.submitList(expenses)
        }

        binding.addButton.setOnClickListener {
            startActivity(Intent(this, AddRecordActivity::class.java))
        }

        binding.logoutButton.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this, LoginPage::class.java))
            finish()
        }

        viewModel.loadExpenses(auth.currentUser?.uid ?: "")
    }
}
