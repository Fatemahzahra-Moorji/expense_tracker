package com.example.expensetracker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.expensetracker.databinding.ActivityAddRecordBinding

class AddRecordActivity {
    class AddRecordActivity : AppCompatActivity() {
        private lateinit var binding: ActivityAddRecordBinding
        private lateinit var viewModel: BudgetViewModel

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityAddRecordBinding.inflate(layoutInflater)
            setContentView(binding.root)

            viewModel = ViewModelProvider(this)[BudgetViewModel::class.java]

            binding.btnSave.setOnClickListener {
                val category = binding.categorySpinner.selectedItem.toString()
                val amount = binding.amountInput.text.toString().toDouble()
                val description = binding.descriptionInput.text.toString()
                val isExpense = binding.isExpenseCheckbox.isChecked

                viewModel.addRecord(Record(category, amount, description, isExpense))
                finish()
            }
        }
    }
}