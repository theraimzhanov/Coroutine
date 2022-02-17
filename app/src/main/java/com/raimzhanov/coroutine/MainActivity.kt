package com.raimzhanov.coroutine

import android.os.*
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.raimzhanov.coroutine.databinding.ActivityMainBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonLoad.setOnClickListener {
            lifecycleScope.launch {
                loadData()
            }
        }
    }

    private suspend fun loadData() {
        Log.d("handler", "loadData: started : $this ")
        binding.progress.isVisible = true
        binding.buttonLoad.isEnabled = false
       val city = loadCity()
        Log.d("handler", "loadData: middle 1 : $this ")
            binding.tvLocation.text = city
       val temp =loadTemp(city)
        Log.d("handler", "loadData: middle 2 : $this ")
                binding.tvTemperature.text = temp.toString()
                binding.progress.isVisible = false
                binding.buttonLoad.isEnabled = true
                Log.d("handler", "loadData: finished : $this ")

    }

    private suspend fun loadTemp(city: String): Int {
        Toast.makeText(this, "Loading temperature for city:$city", Toast.LENGTH_SHORT)
            .show()
        delay(3000)
        return 25
    }

    private suspend fun loadCity(): String {
        delay(3000)
        return "Bishkek"
    }
}