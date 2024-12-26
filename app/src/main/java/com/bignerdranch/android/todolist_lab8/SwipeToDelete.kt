package com.bignerdranch.android.todolist_lab8

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class SwipeToDelete(
    private val adapter: TaskAdapter,
    private val viewModel: TaskViewModel
) : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false // Не реализуем перемещение элементов
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val position = viewHolder.adapterPosition
        val taskToDelete = adapter.getItemAtPosition(position) // Получаем задачу по позиции
        viewModel.delete(taskToDelete) // Удаляем задачу через ViewModel
    }
}