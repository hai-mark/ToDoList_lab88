package com.bignerdranch.android.todolist_lab8

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListActivity : AppCompatActivity() {
    private lateinit var taskViewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val adapter = TaskAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val taskDao = AppDatabase.getDatabase(application).taskDao()
        val factory = TaskViewModelFactory(taskDao)
        taskViewModel = ViewModelProvider(this, factory).get(TaskViewModel::class.java)

        taskViewModel.allTasks.observe(this, { tasks ->
            tasks?.let { adapter.submitList(it) }
        })

        val itemTouchHelper = ItemTouchHelper(SwipeToDelete(adapter, taskViewModel))
        itemTouchHelper.attachToRecyclerView(recyclerView)

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            startActivity(Intent(this, AddActivity::class.java))
        }
    }
}