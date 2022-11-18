package com.droid.demo.api

import android.content.Context
import android.hardware.input.InputManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import android.view.inputmethod.InputMethodManager
import com.droid.demo.R
import com.droid.demo.databinding.ActivityApiFocusBinding
import com.droid.demo.databinding.ActivityApiInputFocusManagerBinding

class InputFocusManagerApiActivity : AppCompatActivity() {

    private lateinit var binding: ActivityApiInputFocusManagerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityApiInputFocusManagerBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        binding.showKeyboardId.setOnClickListener {
            /*it.post {
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(binding.inputActionId.windowToken,InputMethodManager.SHOW_IMPLICIT)
            }*/
            binding.inputActionId.requestFocus()
            binding.inputActionId.post {
                if(binding.inputActionId.hasFocus()){
                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.showSoftInput(it, InputMethodManager.SHOW_IMPLICIT)
                }
            }

        }

        binding.hideKeyboardId.setOnClickListener {

        }
    }


    fun View.focusAndShowKeyboard() {
        /**
         * This is to be called when the window already has focus.
         */
        fun View.showTheKeyboardNow() {
            if (isFocused) {
                post {
                    // We still post the call, just in case we are being notified of the windows focus
                    // but InputMethodManager didn't get properly setup yet.
                    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
                }
            }
        }

        requestFocus()
        if (hasWindowFocus()) {
            // No need to wait for the window to get focus.
            showTheKeyboardNow()
        } else {
            // We need to wait until the window gets focus.
            viewTreeObserver.addOnWindowFocusChangeListener(
                object : ViewTreeObserver.OnWindowFocusChangeListener {
                    override fun onWindowFocusChanged(hasFocus: Boolean) {
                        // This notification will arrive just before the InputMethodManager gets set up.
                        if (hasFocus) {
                            this@focusAndShowKeyboard.showTheKeyboardNow()
                            // It’s very important to remove this listener once we are done.
                            viewTreeObserver.removeOnWindowFocusChangeListener(this)
                        }
                    }
                })
        }
    }
}