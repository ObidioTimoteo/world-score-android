package com.example.worldscore2026.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "partido",
    foreignKeys = [
        ForeignKey(
            entity = EquipoEntity::class,
            parentColumns = ["idEquipo"],
            childColumns = ["idEquipoLocal"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = EquipoEntity::class,
            parentColumns = ["idEquipo"],
            childColumns = ["idEquipoVisitante"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = FaseEntity::class,
            parentColumns = ["idFase"],
            childColumns = ["idFase"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = SedeEntity::class,
            parentColumns = ["idSede"],
            childColumns = ["idSede"],
            onDelete = ForeignKey.CASCADE
        ),
    ],
    indices = [
        Index(value = ["idEquipoLocal"]),
        Index(value = ["idEquipoVisitante"]),
        Index(value = ["idFase"]),
        Index(value = ["idSede"]),
    ]
)

data class PartidoEntity(
    @PrimaryKey val idPartido: Int,
    val idEquipoLocal: String,
    val idEquipoVisitante: String,
    val golesLocal: Int?,
    val golesVisitante: Int?,
    val idFase: String,
    val jornada: Int?,
    val fecha: String,
    val hora: String,
    val idSede: String,
    val placeholderLocal: String?,
    val placeholderVisitante: String?
)