package com.example.expensetracker

data class Record(
    val category: String,
    val amount: Double,
    val description: String,
    val isExpense: Boolean
)