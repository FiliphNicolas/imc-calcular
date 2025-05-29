package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ExercisesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercises)

        val recyclerView = findViewById<RecyclerView>(R.id.rvExercises)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ExercisesAdapter(getExerciseList(), this)
    }

    private fun getExerciseList(): List<Exercise> {
        return listOf(
            Exercise("Sentadillas", "Ejercicio de piernas", R.drawable.sentadilla),
            Exercise("Flexiones", "Ejercicio de pecho", R.drawable.flexiones),
            Exercise("Plancha", "Ejercicio de core", R.drawable.plancha),
            Exercise("Zancadas", "Ejercicio de piernas", R.drawable.zancadas),
            Exercise("Abdominales","Ejercicio de abdomen", R.drawable.abdominales),
            Exercise("Remo", "Ejercicio de pecho", R.drawable.remo),
            Exercise("Burpees", "Ejercicio de fuerza", R.drawable.burpees),
            Exercise("Saltos", "Ejercicio de fuerza", R.drawable.saltos),
        )
    }
}

data class Exercise(val name: String, val description: String, val icon: Int)
