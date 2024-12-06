package com.example.expensetracker

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore

class BudgetViewModel : ViewModel() {

    private val db = FirebaseFirestore.getInstance()
    private val _expenses = MutableLiveData<List<Record>>()
    val expenses: LiveData<List<Record>> get() = _expenses

    fun loadExpenses(userId: String) {
        db.collection("users").document(userId).collection("expenses")
            .addSnapshotListener { value, error ->
                if (error != null || value == null) {
                    return@addSnapshotListener
                }
                val records = value.toObjects(Record::class.java)
                _expenses.value = records
            }
    }
}