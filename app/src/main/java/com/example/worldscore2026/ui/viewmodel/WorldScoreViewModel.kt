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

            // Refrescar fecha actual (cada vez que arranca la app)
            repository.refrescarFechaActual()
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
    fun getClasificacionGrupo(grupo: String) =
        repository.getClasificacionGrupo(grupo)

    // Obtener Partidos por equipo
    fun getPartidosPorEquipo(idEquipo: String) =
        repository.getPartidosPorEquipo(idEquipo)

    // Obtener Partidos por grupo
    fun getPartidosPorGrupo(grupo: String) =
        repository.getPartidosPorGrupo(grupo)

    // Obtener Partidos por fecha
    fun getPartidosPorFecha(fecha: String) =
        repository.getPartidosPorFecha(fecha)

    // Obtener fecha actual
    fun getFechaActual() =
        repository.getFechaActual()

}