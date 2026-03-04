package com.example.worldscore2026.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.worldscore2026.data.local.entity.EquipoEntity
import com.example.worldscore2026.data.local.entity.SedeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SedeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(sedes: List<SedeEntity>)

    @Query("SELECT * FROM sede")
    fun getAllSedes(): Flow<List<SedeEntity>>
}