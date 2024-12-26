package com.bignerdranch.android.todolist_lab8

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class TaskAdapter(private val onTaskClick: (Task) -> Unit) : ListAdapter<Task, TaskAdapter.TaskViewHolder>(TaskDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.task_item, parent, false)
        return TaskViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val currentTask = getItem(position)
        holder.bind(currentTask)
        holder.itemView.setOnClickListener {
            onTaskClick(currentTask)
        }
    }

    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val taskDescription: TextView = itemView.findViewById(R.id.task_description)

        fun bind(task: Task) {
            taskDescription.text = task.description
            when (task.priority) {
                1 -> itemView.setBackgroundColor(0xFFFF0000.toInt()) // Красный для High
                2 -> itemView.setBackgroundColor(0xFFFFFF00.toInt()) // Желтый для Medium
                3 -> itemView.setBackgroundColor(0xFF00FF00.toInt()) // Зеленый для Low
            }
        }
    }

    class TaskDiffCallback : DiffUtil.ItemCallback<Task>() {
        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem == newItem
        }
    }
}
