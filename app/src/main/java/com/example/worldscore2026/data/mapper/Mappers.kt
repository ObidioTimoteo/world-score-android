package com.example.worldscore2026.data.mapper

import com.example.worldscore2026.data.local.entity.*
import com.example.worldscore2026.data.remote.dto.*

/* PAIS */
fun PaisDto.toEntity() = PaisEntity (
    idPais = idPais,
    nombre = nombre
)

/* SEDE */
fun SedeDto.toEntity() = SedeEntity (
    idSede = idSede,
    nombre = nombre,
    idPais = idPais
)

/* FASE */
fun FaseDto.toEntity() = FaseEntity (
    idFase = idFase,
    nombre = nombre
)

/* EQUIPO */
fun EquipoDto.toEntity() = EquipoEntity (
    idEquipo = idEquipo,
    nombre = nombre,
    banderaUrl = banderaUrl,
    grupo = grupo
)

/* PARTIDO */
fun PartidoDto.toEntity() = PartidoEntity (
    idPartido = idPartido,
    idEquipoLocal = idEquipoLocal,
    idEquipoVisitante = idEquipoVisitante,
    golesLocal = golesLocal,
    golesVisitante = golesVisitante,
    idFase = idFase,
    jornada = jornada,
    fecha = fecha,
    hora = hora,
    idSede = idSede
)