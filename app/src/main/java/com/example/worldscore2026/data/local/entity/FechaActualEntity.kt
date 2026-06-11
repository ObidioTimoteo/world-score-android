package com.example.worldscore2026.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fecha_actual")
data class FechaActualEntity(
    @PrimaryKey
    val id: Int = 1,
    val fechaActual: String
)