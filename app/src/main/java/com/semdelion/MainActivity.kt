package com.semdelion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.textfield.TextInputLayout
import com.semdelion.presentation.views.MainFragment

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
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
            val navigationController = findNavController(R.id.fragmentContainerView)
            bottomNavigationView.setupWithNavController(navigationController)
        }
    }
}