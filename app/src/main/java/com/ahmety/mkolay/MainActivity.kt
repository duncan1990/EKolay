package com.ahmety.mkolay

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ahmety.mkolay.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setOnClickListener()
    }

    private fun setOnClickListener() {
        binding.apply {
            viewBackBtnArea.setOnClickListener {
                finish()
            }
        }
    }
}