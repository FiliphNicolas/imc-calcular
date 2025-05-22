package com.example.myapplication

import android.annotation.SuppressLint
import android.database.Cursor
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.IMCHistoryDatabase.Companion.COLUMN_CATEGORY
import com.example.myapplication.IMCHistoryDatabase.Companion.COLUMN_DATE
import com.example.myapplication.IMCHistoryDatabase.Companion.COLUMN_HEIGHT
import com.example.myapplication.IMCHistoryDatabase.Companion.COLUMN_IMC
import com.example.myapplication.IMCHistoryDatabase.Companion.COLUMN_WEIGHT
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HistoryActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: HistoryAdapter
    private lateinit var imcHistoryDatabase: IMCHistoryDatabase
    private lateinit var fabClear: FloatingActionButton
    private var cursor: Cursor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Historial de IMC"

        imcHistoryDatabase = IMCHistoryDatabase(this)
        
        recyclerView = findViewById(R.id.recyclerViewHistory)
        recyclerView.layoutManager = LinearLayoutManager(this)
        
        fabClear = findViewById(R.id.fabClearHistory)
        fabClear.setOnClickListener { clearHistory() }

        loadHistory()
    }

    private fun loadHistory() {
        cursor?.close()
        cursor = imcHistoryDatabase.getAllHistory()
        adapter = HistoryAdapter(cursor!!)
        recyclerView.adapter = adapter
    }

    private fun clearHistory() {
        imcHistoryDatabase.clearHistory()
        Toast.makeText(this, "Historial limpiado", Toast.LENGTH_SHORT).show()
        loadHistory()
    }

    override fun onDestroy() {
        super.onDestroy()
        cursor?.close()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}

class HistoryAdapter(private val cursor: Cursor) : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_history, parent, false)
        return HistoryViewHolder(view)
    }

    @SuppressLint("Range")
    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        cursor.moveToPosition(position)
        
        holder.tvWeight.text = "Peso: ${cursor.getFloat(cursor.getColumnIndex(COLUMN_WEIGHT))} kg"
        holder.tvHeight.text = "Altura: ${cursor.getFloat(cursor.getColumnIndex(COLUMN_HEIGHT))} cm"
        holder.tvIMC.text = "IMC: ${String.format("%.2f", cursor.getFloat(cursor.getColumnIndex(COLUMN_IMC)))}"
        holder.tvCategory.text = cursor.getString(cursor.getColumnIndex(COLUMN_CATEGORY))
        holder.tvDate.text = cursor.getString(cursor.getColumnIndex(COLUMN_DATE))
    }

    override fun getItemCount(): Int = cursor.count

    class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvWeight: TextView = itemView.findViewById(R.id.tvWeight)
        val tvHeight: TextView = itemView.findViewById(R.id.tvHeight)
        val tvIMC: TextView = itemView.findViewById(R.id.tvIMC)
        val tvCategory: TextView = itemView.findViewById(R.id.tvCategory)
        val tvDate: TextView = itemView.findViewById(R.id.tvDate)
    }
}
