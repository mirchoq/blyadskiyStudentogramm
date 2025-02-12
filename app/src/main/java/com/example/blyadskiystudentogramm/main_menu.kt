package com.example.blyadskiystudentogramm

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.blyadskiystudentogramm.databinding.ActivityMainMenuBinding

class main_menu : AppCompatActivity() {

    private lateinit var binding: ActivityMainMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val  nav :BottomNavigationView = binding.navView
        val ff = findNavController(R.id.nav_host_fragment_activity_main_menu)
        nav.setupWithNavController(ff)
    }
}