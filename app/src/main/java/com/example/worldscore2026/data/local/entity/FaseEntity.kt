package com.example.worldscore2026.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fase")
data class FaseEntity(
    @PrimaryKey val idFase: String,
    val nombre: String
)