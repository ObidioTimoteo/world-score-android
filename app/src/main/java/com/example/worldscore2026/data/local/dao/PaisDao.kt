package com.example.worldscore2026.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.worldscore2026.data.local.entity.PaisEntity

@Dao
interface PaisDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(paises: List<PaisEntity>)

    @Query("SELECT * FROM pais")
    suspend fun getAll(): List<PaisEntity>
}