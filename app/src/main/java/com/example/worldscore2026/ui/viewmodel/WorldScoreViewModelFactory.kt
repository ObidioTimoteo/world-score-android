package com.example.worldscore2026.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.worldscore2026.data.repository.WorldScoreRepository

class WorldScoreViewModelFactory(
    private val repository: WorldScoreRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WorldScoreViewModel::class.java)) {
            return WorldScoreViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}