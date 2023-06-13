package com.semdelion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.BindingAdapter
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.textfield.TextInputLayout
import com.semdelion.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {
        @JvmStatic
        @BindingAdapter("app:errorText")
        fun setErrorText(view: TextInputLayout, errorMessage: String?) {
            view.error = errorMessage
        }
    }

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController

        viewBinding.bottomNavigationView.setupWithNavController(navController)
        setSupportActionBar(viewBinding.mainToolbar)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.newsFragment,
                R.id.favoriteNewsFragment,
                R.id.mainFragment
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
    }
}