package com.example.worldscore2026.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.worldscore2026.data.local.entity.PartidoEntity

@Dao
interface PartidoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(partidos: List<PartidoEntity>)

    @Query("SELECT * FROM partido WHERE idFase = :fase")
    suspend fun getPartidosPorFase(fase: String): List<PartidoEntity>

    @Query("SELECT * FROM partido WHERE jornada = :jornada")
    suspend fun getPartidosPorJornada(jornada: Int): List<PartidoEntity>
}