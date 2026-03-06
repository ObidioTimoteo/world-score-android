package com.example.worldscore2026.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.worldscore2026.data.repository.WorldScoreRepository
import kotlinx.coroutines.launch

class WorldScoreViewModel (
    private val repository: WorldScoreRepository
) : ViewModel() {

    // Datos expuestos de la UI
    val equipos = repository.getEquipos()
    val partidos = repository.getPartidosCompletosPorJornada(1)
    val sedes = repository.getSedes()
    val fases = repository.getFases()

    init {
        viewModelScope.launch {
            // Carga inicial (solo una vez)
            repository.cargarDatosIniciales()

            // Refrescar resultados (cada vez que arranca la app)
            repository.refrescarPartidos()
        }
    }

    // Refresco manual si es necesario
    fun refrescarPartidos() {
        viewModelScope.launch {
            repository.refrescarPartidos()
        }
    }
}