package com.example.expensetracker

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BudgetViewModel : ViewModel() {
    private val _records = MutableLiveData<List<Record>>(emptyList())
    val records: LiveData<List<Record>> get() = _records

    fun addRecord(record: Record) {
        _records.value = _records.value?.plus(record)
    }

    fun filterByCategory(category: String) {
        _records.value = _records.value?.filter { it.category == category }
    }
}