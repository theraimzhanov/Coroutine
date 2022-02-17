package com.raimzhanov.coroutine

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.core.view.isVisible
import com.raimzhanov.coroutine.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonLoad.setOnClickListener {

            loadData()
        }
    }

    private fun loadData() {
        binding.progress.isVisible = true
        binding.buttonLoad.isEnabled = false
        binding.tvLocation.text = loadCity()
        binding.tvTemperature.text = loadTemp(loadCity()).toString()
        binding.progress.isVisible = false
        binding.buttonLoad.isEnabled = true

    }

    private fun loadTemp(city: String): Int {
        Toast.makeText(this, "Loading temperature for city:$city", Toast.LENGTH_SHORT)
            .show()
        Thread.sleep(3000)
        return 17
    }

    private fun loadCity(): String {
        Thread.sleep(3000)
        return "Bishkek"
    }
}