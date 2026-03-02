package com.example.worldscore2026.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.worldscore2026.data.local.entity.EquipoEntity
import com.example.worldscore2026.data.local.entity.FaseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FaseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(fases: List<FaseEntity>)

    @Query("SELECT * FROM fase")
    fun getAllFases(): Flow<List<FaseEntity>>

    @Query("SELECT * FROM fase")
    suspend fun getAll(): List<FaseEntity>

    @Query("SELECT * FROM fase WHERE idFase = :id")
    suspend fun getById(id: String): FaseEntity?
}