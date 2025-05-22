package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DietActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diet)

        val recyclerView = findViewById<RecyclerView>(R.id.rvDiet)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = DietAdapter(getDietList(), this)
    }

    private fun getDietList(): List<DietItem> {
        return listOf(
            DietItem("Desayuno", "En la mañana", "300 cal", R.drawable.desayuno),
            DietItem("Almuerzo", "En mediodia o en la tarde", "400 cal", R.drawable.almuerzo),
            DietItem("Cena", "En la noche", "350 cal", R.drawable.cena),
            DietItem("Merienda", "En la tarde", "200 cal", R.drawable.merienda)
        )
    }
}

data class DietItem(val meal: String, val description: String, val calories: String, val icon: Int)
