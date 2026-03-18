package com.example.worldscore2026.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.worldscore2026.data.local.dao.PartidoDao
import com.example.worldscore2026.data.local.relation.PartidoCompleto
import com.example.worldscore2026.data.model.ClasificacionEquipo
import com.example.worldscore2026.data.repository.WorldScoreRepository
import kotlinx.coroutines.flow.Flow
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

    // Partidos por jornada dinámica
    fun getPartidosPorJornada(jornada: Int) =
        repository.getPartidosCompletosPorJornada(jornada)

    // Partidos por fase dinámica
    fun getPartidosPorFase(fase: String) =
        repository.getPartidosPorFase(fase)

    // Obtener Clasificación por grupo
    fun getClasificacionGrupo(grupo: String) = repository.getClasificacionGrupo(grupo)

}