package com.example.worldscore2026.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "equipo")
data class EquipoEntity(
    @PrimaryKey val idEquipo: String,
    val nombre: String,
    val banderaUrl: String,
    val grupo: String
)