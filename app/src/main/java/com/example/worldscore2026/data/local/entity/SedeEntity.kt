package com.example.worldscore2026.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "sede",
    foreignKeys = [
        ForeignKey(
            entity = PaisEntity::class,
            parentColumns = ["idPais"],
            childColumns = ["idPais"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["idPais"])]
)

data class SedeEntity(
    @PrimaryKey val idSede: String,
    val nombre: String,
    val idPais: String
)