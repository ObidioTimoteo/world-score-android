package com.example.worldscore2026.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pais")
data class PaisEntity(
    @PrimaryKey val idPais: String,
    val nombre: String
)