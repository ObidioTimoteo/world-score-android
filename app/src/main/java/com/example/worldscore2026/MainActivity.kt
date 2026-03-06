package com.example.worldscore2026

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.worldscore2026.data.remote.api.RetrofitInstance
import com.example.worldscore2026.data.repository.WorldScoreRepository
import com.example.worldscore2026.ui.viewmodel.WorldScoreViewModel
import com.example.worldscore2026.ui.viewmodel.WorldScoreViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: WorldScoreViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Obtener la BD desde Application
        val db = (application as WorldScoreApp).database

        // Crear repository
        val repository = WorldScoreRepository(RetrofitInstance.api, db)

        // Crear factory
        val factory = WorldScoreViewModelFactory(repository)

        // Crear ViewModel
        viewModel = ViewModelProvider(this,factory)[WorldScoreViewModel::class.java]

        // Navegación
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomNav.setupWithNavController(navController)

    }
}