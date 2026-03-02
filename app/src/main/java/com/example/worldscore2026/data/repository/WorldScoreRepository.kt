package com.example.worldscore2026.data.repository

import com.example.worldscore2026.data.local.database.AppDatabase
import com.example.worldscore2026.data.mapper.toEntity
import com.example.worldscore2026.data.remote.api.ApiService

class WorldScoreRepository (
    private val api: ApiService,
    private val db: AppDatabase
) {
    /**
     * Carga inicial de datos.
     * Se ejecuta solo si la base de datos está vacía.
     * Países, sedes, fases y equipos NO cambiarán durante el torneo
     **/
    suspend fun cargarDatosIniciales() {
        val equiposCount = db.EquipoDao().countEquipos()
        if (equiposCount == 0) {
            cargarDatosEstaticosRemotos()
        }
    }

    suspend fun cargarDatosEstaticosRemotos() {
        with(db) {
            // Descarga datos estáticos desde GitHub y los persiste en Room
            val paises = api.getPaises().map { it.toEntity() }
            db.PaisDao().insertAll(paises)

            val sedes = api.getSedes().map { it.toEntity() }
            db.SedeDao().insertAll(sedes)

            val fases = api.getFases().map { it.toEntity() }
            db.FaseDao().insertAll(fases)

            val equipos = api.getEquipos().map { it.toEntity() }
            db.EquipoDao().insertAll(equipos)
        }
    }

    /**
     * Refresco de datos dinámicos.
     * Los partidos y sus resultados variarán a lo largo del torneo.
     * Este métod_o se llamará al arrancar la app
     **/
    suspend fun refrescarPartidos() {
        with(db) {
            db.PartidoDao().deleteAll()
            val partidos = api.getPartidosJornada1().map { it.toEntity() }
            db.PartidoDao().insertAll(partidos)
        }
    }

    /**
     * Métodos de lectura
     **/
    fun getEquipos() = db.EquipoDao().getAllEquipos()
    fun getPartidos() = db.PartidoDao().getAllPartidos()
    fun getSedes() = db.SedeDao().getAllSedes()
    fun getFases() = db.FaseDao().getAllFases()

}