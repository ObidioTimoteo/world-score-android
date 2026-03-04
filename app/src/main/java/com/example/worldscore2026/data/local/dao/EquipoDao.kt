package com.example.worldscore2026.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.worldscore2026.data.local.entity.EquipoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface EquipoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(equipos: List<EquipoEntity>)

    @Query("SELECT * FROM equipo")
    fun getAllEquipos(): Flow<List<EquipoEntity>>

    @Query("SELECT * FROM equipo WHERE grupo = :grupo")
    fun getEquipoPorGrupo(grupo: String): Flow<List<EquipoEntity>>

    @Query("SELECT COUNT(*) FROM equipo")
    suspend fun countEquipos(): Int
}