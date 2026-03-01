package com.example.worldscore2026

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.room.PrimaryKey
import com.example.worldscore2026.data.local.database.WorldScoreApp
import com.example.worldscore2026.data.local.entity.*
import com.example.worldscore2026.data.mapper.toEntity
import com.example.worldscore2026.data.remote.api.RetrofitInstance
import com.example.worldscore2026.data.test.JsonLoader
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = (application as WorldScoreApp).database

        lifecycleScope.launch {

            // Con Retrofit y Gson obtenemos una lista de PaisDto desde la API de GitHub
            val paisesDto = RetrofitInstance.api.getPaises()

            // Mediante el mapeo lo convertimos a una Entity
            val paisEntities = paisesDto.map { it.toEntity() }

            // Mediante room insertamos la lista de países en la BD
            db.PaisDao().insertAll(paisEntities)

            Log.d("API_TEST", "Insertados ${paisEntities.size} países desde GitHub")
        }

        /* Cuando cargamos datos desde archivos JSON locales para pruebas

        lifecycleScope.launch {

            // 1. Cargar e insertar países de prueba (desde JSON local en assets)
            val paisesDto = JsonLoader.loadPaises(this@MainActivity)
            val paisEntities = paisesDto.map {
                PaisEntity(
                    idPais = it.idPais,
                    nombre = it.nombre
                )
            }
            db.PaisDao().insertAll(paisEntities)
            Log.d("DB_TEST", "Insertados ${paisEntities.size} países")

            // 2. Cargar e insertar sedes de prueba (desde JSON local en assets)
            val sedesDto = JsonLoader.loadSedes(this@MainActivity)
            val sedesEntities = sedesDto.map {
                SedeEntity(
                    idSede = it.idSede,
                    nombre = it.nombre,
                    idPais = it.idPais
                )
            }
            db.SedeDao().insertAll(sedesEntities)
            Log.d("DB_TEST", "Insertadas ${sedesEntities.size} sedes")

            // 3. Cargar e insertar fases de prueba (desde JSON local en assets)
            val fasesDto = JsonLoader.loadFases(this@MainActivity)
            val fasesEntities = fasesDto.map {
                FaseEntity(
                    idFase = it.idFase,
                    nombre = it.nombre
                )
            }
            db.FaseDao().insertAll(fasesEntities)
            Log.d("DB_TEST", "Insertadas ${fasesEntities.size} fases")

            // 4. Cargar e insertar equipos de prueba (desde JSON local en assets)
            val equiposDto = JsonLoader.loadEquipos(this@MainActivity)
            val equiposEntities = equiposDto.map {
                EquipoEntity(
                    idEquipo = it.idEquipo,
                    nombre = it.nombre,
                    banderaUrl = it.banderaUrl,
                    grupo = it.grupo
                )
            }
            db.EquipoDao().insertAll(equiposEntities)
            Log.d("DB_TEST", "Insertados ${equiposEntities.size} equipos")

            // 5. Cargar e insertar partidos de prueba (desde JSON local en assets)
            val partidosDto = JsonLoader.loadPartidos(this@MainActivity)
            val partidosEntities = partidosDto.map {
                PartidoEntity(
                    idPartido = it.idPartido,
                    idEquipoLocal = it.idEquipoLocal,
                    idEquipoVisitante = it.idEquipoVisitante,
                    golesLocal = it.golesLocal,
                    golesVisitante = it.golesVisitante,
                    idFase = it.idFase,
                    jornada = it.jornada,
                    fecha = it.fecha,
                    hora = it.hora,
                    idSede = it.idSede
                )
            }
            db.PartidoDao().insertAll(partidosEntities)
            Log.d("DB_TEST", "Insertados ${partidosEntities.size} partidos")
        }

        */
    }
}