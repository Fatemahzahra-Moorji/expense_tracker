package com.example.expensetracker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.expensetracker.databinding.RecordItemBinding

class BudgetAdapter : RecyclerView.Adapter<BudgetAdapter.ExpenseViewHolder>() {

    private var expenses: MutableList<Record> = mutableListOf()

    inner class ExpenseViewHolder(private val binding: RecordItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(expense: Record) {
            binding.categoryTextView.text = expense.category
            binding.amountTextView.text = expense.amount.toString()
            binding.descriptionTextView.text = expense.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        val binding = RecordItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExpenseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        holder.bind(expenses[position])
    }

    override fun getItemCount(): Int = expenses.size

    fun submitList(newRecords: List<Record>) {
        expenses.clear()
        expenses.addAll(newRecords)
        notifyDataSetChanged()
    }
}