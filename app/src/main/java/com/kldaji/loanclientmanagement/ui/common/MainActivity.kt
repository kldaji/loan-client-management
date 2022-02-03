package com.kldaji.loanclientmanagement.ui.common

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.kldaji.loanclientmanagement.R
import com.kldaji.loanclientmanagement.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val navHostFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.nav_host_main) as NavHostFragment
    }
    private val navController by lazy { navHostFragment.findNavController() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setNavController()
        setBottomNavigationVisibility()
    }

    private fun setNavController() {
        binding.bnvMain.setupWithNavController(navController)
    }

    private fun setBottomNavigationVisibility() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.clientSearchFragment -> binding.bnvMain.isVisible = false
                else -> binding.bnvMain.isVisible = true
            }
        }
    }
}
