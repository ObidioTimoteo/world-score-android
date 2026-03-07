package com.example.worldscore2026.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.worldscore2026.data.local.entity.PartidoEntity
import com.example.worldscore2026.data.local.relation.PartidoCompleto
import kotlinx.coroutines.flow.Flow

@Dao
interface PartidoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(partidos: List<PartidoEntity>)

    @Query("SELECT * FROM partido")
    fun getAllPartidos(): Flow<List<PartidoEntity>>

    @Query("SELECT * FROM partido WHERE jornada = :jornada")
    fun getPartidosPorJornada(jornada: Int): Flow<List<PartidoEntity>>

    @Query("DELETE FROM partido")
    suspend fun deleteAll()

    @Query("SELECT COUNT(*) FROM partido")
    suspend fun countPartidos(): Int

    // Query con relaciones
    @Transaction
    @Query("SELECT * FROM partido WHERE jornada = :jornada")
    fun getPartidosCompletosPorJornada(jornada: Int): Flow<List<PartidoCompleto>>

    @Transaction
    @Query("SELECT * FROM partido WHERE idFase = :fase")
    fun getPartidosPorFase(fase: String): Flow<List<PartidoCompleto>>

}