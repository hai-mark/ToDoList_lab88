package com.bignerdranch.android.todolist_lab8

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListActivity : AppCompatActivity() {
    private lateinit var taskViewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val adapter = TaskAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        taskViewModel.allTasks.observe(this, { tasks ->
            tasks?.let { adapter.submitList(it) }
        })

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            startActivity(Intent(this, AddActivity::class.java))
        }
    }
}