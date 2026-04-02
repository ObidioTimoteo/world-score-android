package com.example.worldscore2026.data.remote.dto

data class PartidoDto (
    val idPartido: Int,
    val idEquipoLocal: String,
    val idEquipoVisitante: String,
    val golesLocal: Int?,
    val golesVisitante: Int?,
    val penaltisLocal: Int? = null,
    val penaltisVisitante: Int? = null,
    val idFase: String,
    val jornada: Int?,
    val fecha: String,
    val hora: String,
    val idSede: String,
    val placeholderLocal: String? = null,
    val placeholderVisitante: String? = null
)