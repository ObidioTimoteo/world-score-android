package com.example.worldscore2026.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.worldscore2026.data.local.entity.EquipoEntity
import com.example.worldscore2026.data.local.entity.PartidoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PartidoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(partidos: List<PartidoEntity>)

    @Query("SELECT * FROM partido")
    fun getAllPartidos(): Flow<List<PartidoEntity>>

    @Query("SELECT * FROM partido WHERE idFase = :fase")
    suspend fun getPartidosPorFase(fase: String): Flow<List<PartidoEntity>>

    @Query("SELECT * FROM partido WHERE jornada = :jornada")
    suspend fun getPartidosPorJornada(jornada: Int): Flow<List<PartidoEntity>>

    @Query("DELETE FROM partido")
    suspend fun deleteAll()
}