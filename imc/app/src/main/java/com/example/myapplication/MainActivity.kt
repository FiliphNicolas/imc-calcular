package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var etWeight: EditText
    private lateinit var etHeight: EditText
    private lateinit var btnCalculate: Button
    private lateinit var tvResult: TextView
    private lateinit var tvCategory: TextView
    private var defaultCategoryTextColor: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etWeight = findViewById(R.id.etWeight)
        etHeight = findViewById(R.id.etHeight)
        btnCalculate = findViewById(R.id.btnCalculate)
        tvResult = findViewById(R.id.tvResult)
        tvCategory = findViewById(R.id.tvCategory)

        defaultCategoryTextColor = tvCategory.currentTextColor

        btnCalculate.setOnClickListener {
            val sWeight = etWeight.text.toString()
            val sHeight = etHeight.text.toString()

            tvCategory.setTextColor(defaultCategoryTextColor)

            if (sWeight.isNotEmpty() && sHeight.isNotEmpty()) {
                try {
                    val weight = sWeight.toFloat()
                    val height = sHeight.toFloat() / 100
                    val imc = weight / (height * height)

                    val categoryResult: Pair<Int, String> = when {
                        imc < 18.5 -> Pair(ContextCompat.getColor(this, R.color.desnutricion), getString(R.string.desnutricion))
                        imc < 25 -> Pair(ContextCompat.getColor(this, R.color.peso_ideal), getString(R.string.peso_ideal))
                        imc < 30 -> Pair(ContextCompat.getColor(this, R.color.sobrepeso), getString(R.string.sobrepeso))
                        imc < 35 -> Pair(ContextCompat.getColor(this, R.color.obesidad_i), getString(R.string.obesidad_i))
                        imc < 40 -> Pair(ContextCompat.getColor(this, R.color.obesidad_ii), getString(R.string.obesidad_ii))
                        imc < 50 -> Pair(ContextCompat.getColor(this, R.color.obesidad_iii), getString(R.string.obesidad_morbida))
                        else -> Pair(ContextCompat.getColor(this, R.color.obesidad_iv), getString(R.string.obesidad_extrema))
                    }

                    tvResult.text = String.format(Locale.getDefault(), getString(R.string.imc_result_format), imc)
                    tvCategory.text = getString(R.string.category_format, categoryResult.second)

                    tvCategory.setTextColor(categoryResult.first)

                } catch (e: NumberFormatException) {
                    tvResult.text = getString(R.string.error_message)
                    tvCategory.text = ""
                }
            } else {
                tvResult.text = getString(R.string.complete_fields_message)
                tvCategory.text = ""
            }
        }
    }
}