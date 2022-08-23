package com.belt.animequote.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.belt.animequote.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityMainBinding
            .inflate(layoutInflater)
            .root
            .let(::setContentView)
    }
}