package com.raimzhanov.coroutine

import android.os.*
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import androidx.core.view.isVisible
import com.raimzhanov.coroutine.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val handler = object: Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            Log.d("TAG", "handleMessage:$msg ")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonLoad.setOnClickListener {
            loadData()
        }
        handler.sendMessage(Message.obtain(handler,1,"hard work"))
    }

    private fun loadData() {
        binding.progress.isVisible = true
        binding.buttonLoad.isEnabled = false
        loadCity { it ->
            binding.tvLocation.text = it
            loadTemp(it) {
                binding.tvTemperature.text = it.toString()
                binding.progress.isVisible = false
                binding.buttonLoad.isEnabled = true
            }
        }
    }

    private fun loadTemp(city: String, callback: (Int) -> Unit) {
        thread {
            runOnUiThread {
                Toast.makeText(this, "Loading temperature for city:$city", Toast.LENGTH_SHORT)
                    .show()
            }
            Thread.sleep(3000)
            runOnUiThread { callback.invoke(25) }
        }
    }

    private fun loadCity(callback: (String) -> Unit) {
        thread {
            Thread.sleep(3000)
            runOnUiThread { callback.invoke("Bishkek") }
        }

    }
}