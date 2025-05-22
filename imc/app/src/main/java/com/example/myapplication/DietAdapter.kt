package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DietAdapter(private val dietItems: List<DietItem>, private val context: Context) : RecyclerView.Adapter<DietAdapter.DietViewHolder>() {

    class DietViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivDiet: ImageView = itemView.findViewById(R.id.ivDiet)
        val tvMeal: TextView = itemView.findViewById(R.id.tvMeal)
        val tvDescription: TextView = itemView.findViewById(R.id.tvDescription)
        val tvCalories: TextView = itemView.findViewById(R.id.tvCalories)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DietViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.item_diet, parent, false)
        return DietViewHolder(view)
    }

    override fun onBindViewHolder(holder: DietViewHolder, position: Int) {
        val dietItem = dietItems[position]
        holder.tvMeal.text = dietItem.meal
        holder.tvDescription.text = dietItem.description
        holder.tvCalories.text = dietItem.calories
        holder.ivDiet.setImageResource(dietItem.icon)
    }

    override fun getItemCount() = dietItems.size
}
