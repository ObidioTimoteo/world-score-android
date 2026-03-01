package com.example.worldscore2026.data.local.database

import android.app.Application

class WorldScoreApp : Application() {
    val database by lazy {
        AppDatabase.getInstance(this)
    }
}