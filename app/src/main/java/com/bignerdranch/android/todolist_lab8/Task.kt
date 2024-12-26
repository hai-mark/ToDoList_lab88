package com.bignerdranch.android.todolist_lab8

import androidx.room.Entity
import androidx.room.PrimaryKey

class Task {
    @Entity(tableName = "tasks")
    data class Task(
        @PrimaryKey(autoGenerate = true) val id: Long = 0,
        val description: String,
        val priority: Int // 1 - High, 2 - Medium, 3 - Low
    )
}