package com.ahmety.mkolay

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import com.ahmety.mkolay.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setDefaultTab()
        setStatusBarColor()
        setBottomNav()
    }

    private fun setDefaultTab() {
        binding.apply {
            bottomNavigationView.selectedItemId = R.id.mkolay
        }
    }

    private fun setStatusBarColor() {
        val window = this.window
        window.statusBarColor = ContextCompat.getColor(this, R.color.zest)
    }

    private fun setBottomNav() {
        val bottomNav = binding.bottomNavigationView
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        navController.addOnDestinationChangedListener { _, destination, _ ->
            bottomNav.isVisible = when (destination.id) {
                R.id.mainFragment -> true
                else -> false
            }
        }
    }

}