package com.belt.animequote.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.belt.animequote.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setUpNavigation(binding)
    }

    private fun setUpNavigation(binding: ActivityMainBinding) {
        val navController = binding.fragmentContainerView.getFragment<NavHostFragment>().navController
        binding.bottomNav.setupWithNavController(navController)
    }
}