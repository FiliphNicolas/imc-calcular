package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ActivityNutritionistBinding

class NutritionistActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNutritionistBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNutritionistBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imc = intent.getFloatExtra("IMC", 0f)
        val bmiCategory = getBMICategory(imc)
        
        binding.tvNutritionistTitle.text = "Recomendaciones Nutricionales"
        if (bmiCategory.isEmpty()) {
            binding.tvBmiCategory.text = "No se pudo calcular la categoría BMI"
            binding.tvImcValue.text = ""
        } else {
            binding.tvBmiCategory.text = "Categoría BMI: $bmiCategory"
            binding.tvImcValue.text = "IMC: ${String.format("%.2f", imc)}"
        }

        setupDietRecommendations(bmiCategory)
        setupNutritionalTips()
    }

    private fun getBMICategory(imc: Float): String {
        return when {
            imc <= 0f -> ""
            imc < 18.5 -> "Bajo peso"
            imc < 25 -> "Peso normal"
            imc < 30 -> "Sobrepeso"
            else -> "Obesidad"
        }
    }

    private fun setupDietRecommendations(bmiCategory: String) {
        if (bmiCategory.isEmpty()) {
            binding.tvBmiCategory.text = "No se pudo calcular la categoría BMI"
            binding.tvImcValue.text = ""
            return
        }

        val recommendations = when (bmiCategory) {
            "Bajo peso" -> listOf(
                "Aumentar la ingesta calórica de manera saludable",
                "Incluir más proteínas y carbohidratos complejos",
                "Comer 5-6 comidas al día",
                "Incluir alimentos ricos en grasas saludables"
            )
            "Peso normal" -> listOf(
                "Mantener una dieta equilibrada",
                "Incluir frutas y vegetales en todas las comidas",
                "Controlar el tamaño de las porciones",
                "Mantener una rutina de ejercicio regular"
            )
            "Sobrepeso" -> listOf(
                "Reducir el consumo de azúcares y grasas saturadas",
                "Aumentar el consumo de fibra",
                "Controlar el tamaño de las porciones",
                "Incluir más alimentos integrales"
            )
            "Obesidad" -> listOf(
                "Reducir drásticamente el consumo de azúcares y grasas",
                "Aumentar el consumo de proteínas magras",
                "Incluir más vegetales y frutas",
                "Realizar ejercicio cardiovascular regular"
            )
            else -> listOf()
        }

        val recyclerView = binding.rvRecommendations
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = NutritionAdapter(recommendations)
    }

    private fun setupNutritionalTips() {
        val tips = listOf(
            "Beber al menos 2 litros de agua al día",
            "Incluir proteínas en cada comida",
            "Evitar alimentos procesados y ultraprocesados",
            "Incluir una variedad de colores en tu dieta",
            "Mantener horarios regulares de comidas",
            "Mantener una dieta equilibrada",
            "Realizar ejercicio regularmente"
        )

        val recyclerView = binding.rvNutritionalTips
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = NutritionAdapter(tips)
    }
}

data class NutritionItem(val title: String)

class NutritionAdapter(private val items: List<String>) : RecyclerView.Adapter<NutritionAdapter.ViewHolder>() {
    class ViewHolder(itemView: android.view.View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<android.widget.TextView>(R.id.tvNutritionItem)
    }

    override fun onCreateViewHolder(parent: android.view.ViewGroup, viewType: Int): ViewHolder {
        val view = android.view.LayoutInflater.from(parent.context)
            .inflate(R.layout.item_nutrition, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = items[position]
    }

    override fun getItemCount() = items.size
}
