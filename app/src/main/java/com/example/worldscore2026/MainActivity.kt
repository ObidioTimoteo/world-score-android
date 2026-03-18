package com.example.worldscore2026

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.worldscore2026.data.remote.api.RetrofitInstance
import com.example.worldscore2026.data.repository.WorldScoreRepository
import com.example.worldscore2026.ui.viewmodel.WorldScoreViewModel
import com.example.worldscore2026.ui.viewmodel.WorldScoreViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.Locale

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: WorldScoreViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        // Leemos las preferencias guardadas
        val prefs = getSharedPreferences("settings", Context.MODE_PRIVATE)

        // Aplicar modo oscuro guardado
        val darkMode = prefs.getBoolean("dark_mode", false)

        if(darkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        // Aplicar tema de color
        val theme = prefs.getString("theme", "default")

        when(theme) {
            "canada" -> setTheme(R.style.Theme_WorldScore2026_Canada)
            "usa" -> setTheme(R.style.Theme_WorldScore2026_USA)
            "mexico" -> setTheme(R.style.Theme_WorldScore2026_Mexico)
            else -> setTheme(R.style.Theme_WorldScore2026_Default)
        }

        // Aplicar idioma
        val language = prefs.getString("language", "es")

        val locale = Locale(language!!)
        Locale.setDefault(locale)

        val config = resources.configuration
        config.setLocale(locale)

        resources.updateConfiguration(config, resources.displayMetrics)


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