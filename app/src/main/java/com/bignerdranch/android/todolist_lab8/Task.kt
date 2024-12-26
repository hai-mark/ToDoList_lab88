package com.bignerdranch.android.todolist_lab8

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val description: String,
    val priority: Int
)