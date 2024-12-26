package com.bignerdranch.android.todolist_lab8

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query

@Dao
interface TaskDao {
    @Insert
    suspend fun insert(task: Task)

    @Query("SELECT * FROM tasks ORDER BY priority ASC")
    fun getAllTasks(): LiveData<List<Task>>

    @Delete
    suspend fun delete(task: Task)
}