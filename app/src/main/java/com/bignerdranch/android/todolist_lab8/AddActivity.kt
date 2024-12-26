package com.bignerdranch.android.todolist_lab8

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddActivity : AppCompatActivity() {

    private lateinit var taskDao: TaskDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        taskDao = AppDatabase.getDatabase(application).taskDao()

        findViewById<Button>(R.id.add_button).setOnClickListener {
            val description = findViewById<EditText>(R.id.description_input).text.toString()
            val priority = when (findViewById<RadioGroup>(R.id.priority_group).checkedRadioButtonId) {
                R.id.high_priority -> 1 // High priority
                R.id.medium_priority -> 2 // Medium priority
                else -> 3 // Low priority by default
            }

            val task = Task(description = description, priority = priority)
            // Используйте корутины для вставки задачи в базу данных
            insertTask(task)
            finish()
        }
    }

    private fun insertTask(task: Task) {
        CoroutineScope(Dispatchers.IO).launch {
            taskDao.insert(task)
        }
    }
}