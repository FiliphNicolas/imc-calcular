package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private lateinit var etWeight: EditText
    private lateinit var etHeight: EditText
    private lateinit var btnCalculate: Button
    private lateinit var tvResult: TextView
    private lateinit var tvResultNum: TextView
    private lateinit var tvCategory: TextView
    private lateinit var tvCategoryClass: TextView
    private lateinit var tvError: TextView
    private var defaultCategoryTextColor: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        setupCalculateButton()
    }

    private fun initViews() {
        etWeight = findViewById(R.id.etWeight)
        etHeight = findViewById(R.id.etHeight)
        btnCalculate = findViewById(R.id.btnCalculate)
        tvResult = findViewById(R.id.tvResult)
        tvResultNum = findViewById(R.id.tvResultNum)
        tvCategory = findViewById(R.id.tvCategory)
        tvCategoryClass = findViewById(R.id.tvCategoryClass)
        tvError = findViewById(R.id.tvError)
        defaultCategoryTextColor = tvCategory.currentTextColor
    }

    private fun setupCalculateButton() {
        btnCalculate.setOnClickListener {
            calculateAndDisplayIMC()
        }
    }


    private fun validateInputs(weight: Float, height: Float): Boolean {
        return when {
            etWeight.text.isEmpty() || etHeight.text.isEmpty() -> {
                showError(getString(R.string.complete_fields_message))
                false
            }
            weight <= 0f || height <= 0f -> {
                showError(getString(R.string.positive_values_message))
                false
            }
            weight < 20f || weight > 300f -> {
                showError(getString(R.string.invalid_weight_range))
                false
            }
            height < 50f || height > 250f -> {
                showError(getString(R.string.invalid_height_range))
                false
            }
            else -> true
        }
    }
    private fun calculateAndDisplayIMC() {
        tvCategoryClass.setTextColor(defaultCategoryTextColor)

        try {
            val weight = etWeight.text.toString().toFloat()
            val height = etHeight.text.toString().toFloat()

            if (!validateInputs(weight, height)) return

            val imc = calculateIMC(weight, height)
            displayResults(imc)

        } catch (e: NumberFormatException) {
            showError(getString(R.string.error_message))
        } catch (e: Exception) {
            showError(getString(R.string.unexpected_error))
        }
    }
    private fun calculateIMC(weight: Float, heightCm: Float): Float {
        val heightMeters = heightCm / 100
        return weight / (heightMeters * heightMeters)
    }

    @SuppressLint("DefaultLocale")
    private fun displayResults(imc: Float) {
        val category = getIMCCategory(imc)

        tvResultNum.text = String.format("%.2f", imc)
        tvCategoryClass.text = category
        tvError.text = ""
        tvCategoryClass.setTextColor(ContextCompat.getColor(this, getColorForCategory(category)))
    }

    private fun getIMCCategory(imc: Float): String {
        return when {
            imc < 18.5 -> getString(R.string.desnutricion)
            imc < 25 -> getString(R.string.peso_ideal)
            imc < 30 -> getString(R.string.sobrepeso)
            imc < 35 -> getString(R.string.obesidad_i)
            imc < 40 -> getString(R.string.obesidad_ii)
            imc < 50 -> getString(R.string.obesidad_morbida)
            else -> getString(R.string.obesidad_extrema)
        }
    }

    private fun getColorForCategory(category: String): Int {
        return when (category) {
            getString(R.string.desnutricion) -> R.color.desnutricion
            getString(R.string.peso_ideal) -> R.color.peso_ideal
            getString(R.string.sobrepeso) -> R.color.sobrepeso
            getString(R.string.obesidad_i) -> R.color.obesidad_i
            getString(R.string.obesidad_ii) -> R.color.obesidad_ii
            getString(R.string.obesidad_morbida) -> R.color.obesidad_iii
            else -> R.color.obesidad_iv
        }
    }

    private fun showError(message: String) {
        tvError.text = message
        tvResultNum.text = ""
        tvCategoryClass.text = ""
        tvCategoryClass.setTextColor(defaultCategoryTextColor)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_about -> {
                startActivity(Intent(this, AboutActivity::class.java))
                true
            }
            R.id.action_exercises -> {
                startActivity(Intent(this, ExercisesActivity::class.java))
                true
            }
            R.id.action_diet -> {
                startActivity(Intent(this, DietActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
