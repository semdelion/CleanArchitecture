package com.semdelion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout
import com.semdelion.presentation.MainFragment

class MainActivity : AppCompatActivity() {

    companion object {
        @JvmStatic
        @BindingAdapter("app:errorText")
        fun setErrorText(view: TextInputLayout, errorMessage: String?) {
            view.error = errorMessage
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("Activity", "created")
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }
}