package com.example.myapplication

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Acerca de"

        // Set up the about content
        binding.tvAppName.text = "IMC Calculator"
        binding.tvVersion.text = "Versión 1.1"
        binding.tvDescription.text = "Calculadora de Índice de Masa Corporal (IMC) que ayuda a determinar si tu peso es saludable."
        binding.tvDeveloper.text = "Desarrollado por: Filiph Nicolas"
        binding.tvEmail.text = "fsuan62@uan.edu.co"
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
