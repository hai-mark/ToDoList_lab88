package com.bignerdranch.android.todolist_lab8

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class AddActivity : AppCompatActivity() {
    private lateinit var taskViewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        findViewById<Button>(R.id.add_button).setOnClickListener {
            val description = findViewById<EditText>(R.id.description_input).text.toString()
            val priority = when (findViewById<RadioGroup>(R.id.priority_group).checkedRadioButtonId) {
                R.id.high_priority -> 1
                R.id.medium_priority -> 2
                else -> 3
            }

            val task = Task(description = description, priority = priority)
            taskViewModel.insert(task)
            finish()
        }
    }
}