package com.example.worldscore2026.data.model

import com.example.worldscore2026.data.local.entity.EquipoEntity

data class ClasificacionEquipo (
    val equipo: EquipoEntity,
    val pj: Int,
    val puntos: Int,
    val diferencia: Int,
    val golesFavor: Int,
    val isHeader: Boolean = false
)