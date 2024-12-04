package com.example.expensetracker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.expensetracker.databinding.RecordItemBinding

class BudgetAdapter : ListAdapter<Record, BudgetAdapter.BudgetViewHolder>(DiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BudgetViewHolder {
        val binding = RecordItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BudgetViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BudgetViewHolder, position: Int) {
        val record = getItem(position)
        holder.bind(record)
    }

    class BudgetViewHolder(private val binding: RecordItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(record: Record) {
            binding.categoryText.text = record.category
            binding.amountText.text = "${if (record.isExpense) "-" else "+"} $${record.amount}"
            binding.descriptionText.text = record.description
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Record>() {
        override fun areItemsTheSame(oldItem: Record, newItem: Record): Boolean = oldItem == newItem
        override fun areContentsTheSame(oldItem: Record, newItem: Record): Boolean = oldItem == newItem
    }
}