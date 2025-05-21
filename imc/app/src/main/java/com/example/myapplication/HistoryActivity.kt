package com.example.myapplication

import android.database.Cursor
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HistoryActivity : AppCompatActivity() {
    private lateinit var imcHistoryDatabase: IMCHistoryDatabase
    private lateinit var adapter: HistoryAdapter
    private lateinit var cursor: Cursor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        imcHistoryDatabase = IMCHistoryDatabase(this)
        setupRecyclerView()
        setupClearButton()
    }

    private fun setupRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.rvHistory)
        cursor = imcHistoryDatabase.getAllHistory()
        adapter = HistoryAdapter(this, cursor)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun setupClearButton() {
        val btnClearHistory = findViewById<Button>(R.id.btnClearHistory)
        btnClearHistory.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle(getString(R.string.clear_history))
                .setMessage(getString(R.string.clear_history_confirmation))
                .setPositiveButton(getString(R.string.yes)) { _, _ ->
                    imcHistoryDatabase.clearHistory()
                    cursor = imcHistoryDatabase.getAllHistory()
                    adapter.swapCursor(cursor)
                }
                .setNegativeButton(getString(R.string.no), null)
                .show()
        }
    }

    override fun onDestroy() {
        cursor.close()
        super.onDestroy()
    }
}
