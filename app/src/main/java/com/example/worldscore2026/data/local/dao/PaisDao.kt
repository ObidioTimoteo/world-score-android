package com.example.worldscore2026.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.worldscore2026.data.local.entity.EquipoEntity
import com.example.worldscore2026.data.local.entity.PaisEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PaisDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(paises: List<PaisEntity>)

    @Query("SELECT * FROM pais")
    fun getAllPaises(): Flow<List<PaisEntity>>

}