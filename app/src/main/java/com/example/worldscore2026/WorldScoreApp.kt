package com.example.worldscore2026

import android.app.Application
import com.example.worldscore2026.data.local.database.AppDatabase

class WorldScoreApp : Application() {
    val database by lazy {
        AppDatabase.getInstance(this)
    }
}