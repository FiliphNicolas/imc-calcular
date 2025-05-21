package com.example.myapplication

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
    private lateinit var imcHistoryDatabase: IMCHistoryDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imcHistoryDatabase = IMCHistoryDatabase(this)
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

    private fun calculateIMC(weight: Float, heightCm: Float): Float {
        val heightMeters = heightCm / 100
        return weight / (heightMeters * heightMeters)
    }

    private fun displayResults(imc: Float) {
        val (colorRes, categoryRes) = when {
            imc < 18.5 -> Pair(R.color.desnutricion, R.string.desnutricion)
            imc < 25 -> Pair(R.color.peso_ideal, R.string.peso_ideal)
            imc < 30 -> Pair(R.color.sobrepeso, R.string.sobrepeso)
            imc < 35 -> Pair(R.color.obesidad_i, R.string.obesidad_i)
            imc < 40 -> Pair(R.color.obesidad_ii, R.string.obesidad_ii)
            imc < 50 -> Pair(R.color.obesidad_iii, R.string.obesidad_morbida)
            else -> Pair(R.color.obesidad_iv, R.string.obesidad_extrema)
        }

        // Guardar en el historial
        val category = getString(categoryRes)
        imcHistoryDatabase.addHistory(
            etWeight.text.toString().toFloat(),
            etHeight.text.toString().toFloat(),
            imc,
            category
        )

        tvResultNum.text = getString(R.string.imc_result_format, imc)
        tvCategoryClass.text = getString(categoryRes)
        tvError.text = ""
        tvCategoryClass.setTextColor(ContextCompat.getColor(this, colorRes))
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
            R.id.action_history -> {
                startActivity(Intent(this, HistoryActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}