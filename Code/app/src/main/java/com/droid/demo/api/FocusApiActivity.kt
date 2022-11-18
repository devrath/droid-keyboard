package com.droid.demo.api

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.droid.demo.R
import com.droid.demo.databinding.ActivityApiFocusBinding

class FocusApiActivity : AppCompatActivity() {

    private lateinit var binding: ActivityApiFocusBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityApiFocusBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        binding.showKeyboardId.setOnClickListener {
            binding.inputActionId.requestFocus()
        }

        binding.showKeyboardId.setOnClickListener {
            window.decorView.clearFocus();
        }
    }
}