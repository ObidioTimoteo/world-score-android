package com.example.worldscore2026.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.worldscore2026.data.local.dao.*
import com.example.worldscore2026.data.local.entity.*

@Database(
    entities = [
        EquipoEntity::class,
        FaseEntity::class,
        PaisEntity::class,
        PartidoEntity::class,
        SedeEntity::class
    ],
    version = 2
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun EquipoDao(): EquipoDao
    abstract fun FaseDao(): FaseDao
    abstract fun PaisDao(): PaisDao
    abstract fun PartidoDao(): PartidoDao
    abstract fun SedeDao(): SedeDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "worldscore_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }

}