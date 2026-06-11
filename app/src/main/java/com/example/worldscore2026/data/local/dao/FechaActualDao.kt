package com.example.worldscore2026.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.worldscore2026.data.local.entity.FechaActualEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FechaActualDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(fecha: FechaActualEntity)

    @Query("SELECT * FROM fecha_actual WHERE id = 1")
    fun getFechaActual(): Flow<FechaActualEntity?>
}