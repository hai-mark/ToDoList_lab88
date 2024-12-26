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
    private var taskId: Long = -1 // ID задачи для редактирования

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        taskDao = AppDatabase.getDatabase(application).taskDao()

        // Получаем данные задачи, если они были переданы
        val descriptionInput = findViewById<EditText>(R.id.description_input)
        val priorityGroup = findViewById<RadioGroup>(R.id.priority_group)

        taskId = intent.getLongExtra("task_id", -1)
        val taskDescription = intent.getStringExtra("task_description")
        val taskPriority = intent.getIntExtra("task_priority", 3)

        if (taskId != -1L) { // Редактируем задачу
            descriptionInput.setText(taskDescription)
            when (taskPriority) {
                1 -> priorityGroup.check(R.id.high_priority)
                2 -> priorityGroup.check(R.id.medium_priority)
                3 -> priorityGroup.check(R.id.low_priority)
            }
        }

        findViewById<Button>(R.id.add_button).setOnClickListener {
            val description = descriptionInput.text.toString()
            val priority = when (priorityGroup.checkedRadioButtonId) {
                R.id.high_priority -> 1
                R.id.medium_priority -> 2
                else -> 3
            }

            if (taskId == -1L) { // Новая задача
                val task = Task(description = description, priority = priority)
                insertTask(task)
            } else { // Обновление существующей задачи
                val task = Task(id = taskId, description = description, priority = priority)
                updateTask(task)
            }
            finish()
        }
    }

    private fun insertTask(task: Task) {
        CoroutineScope(Dispatchers.IO).launch {
            taskDao.insert(task)
        }
    }

    private fun updateTask(task: Task) {
        CoroutineScope(Dispatchers.IO).launch {
            taskDao.update(task)
        }
    }
}
