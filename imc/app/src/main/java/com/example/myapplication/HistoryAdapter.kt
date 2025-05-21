package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HistoryAdapter(
    private val context: Context,
    private val cursor: Cursor
) : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvWeight: TextView = itemView.findViewById(R.id.tvWeight)
        val tvHeight: TextView = itemView.findViewById(R.id.tvHeight)
        val tvIMC: TextView = itemView.findViewById(R.id.tvIMC)
        val tvCategory: TextView = itemView.findViewById(R.id.tvCategory)
        val tvDate: TextView = itemView.findViewById(R.id.tvDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.history_item, parent, false)
        return HistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        cursor.moveToPosition(position)
        
        holder.tvWeight.text = "Peso: ${cursor.getFloat(cursor.getColumnIndex(IMCHistoryDatabase.COLUMN_WEIGHT))} kg"
        holder.tvHeight.text = "Altura: ${cursor.getFloat(cursor.getColumnIndex(IMCHistoryDatabase.COLUMN_HEIGHT))} cm"
        holder.tvIMC.text = "IMC: ${cursor.getFloat(cursor.getColumnIndex(IMCHistoryDatabase.COLUMN_IMC))}"
        holder.tvCategory.text = "Categoría: ${cursor.getString(cursor.getColumnIndex(IMCHistoryDatabase.COLUMN_CATEGORY))}"
        holder.tvDate.text = "Fecha: ${cursor.getString(cursor.getColumnIndex(IMCHistoryDatabase.COLUMN_DATE))}"
    }

    override fun getItemCount(): Int = cursor.count

    fun swapCursor(newCursor: Cursor) {
        cursor.close()
        cursor = newCursor
        notifyDataSetChanged()
    }
}
