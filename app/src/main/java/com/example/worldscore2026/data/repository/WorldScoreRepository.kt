package com.example.worldscore2026.data.repository

import android.util.Log
import androidx.room.withTransaction
import com.example.worldscore2026.data.local.database.AppDatabase
import com.example.worldscore2026.data.local.entity.EquipoEntity
import com.example.worldscore2026.data.local.relation.PartidoCompleto
import com.example.worldscore2026.data.mapper.toEntity
import com.example.worldscore2026.data.model.ClasificacionEquipo
import com.example.worldscore2026.data.remote.api.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

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
        val partidosCount = db.PartidoDao().countPartidos()
        if (partidosCount == 0) {
            cargarDatosEstaticosRemotos()
        }
    }

    suspend fun cargarDatosEstaticosRemotos() {
        db.withTransaction {
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
        try {
            db.withTransaction {
                db.PartidoDao().deleteAll()
                val partidos = api.getPartidos().map { it.toEntity() }
                db.PartidoDao().insertAll(partidos)
            }
        } catch (e: Exception) {
            Log.e("DB", "Error insertando partidos", e)
        }
    }

    /**
     * Clasificación por grupo:
     * 1.- Cogemos todos los partidos
     * 2.- Nos quedamos con los partidos en el que el equipo local está en el grupo indicado
     * 3.- Calculamos la clasificación de ese grupo
     */
    fun getClasificacionGrupo(grupo: String): Flow<List<ClasificacionEquipo>> {
        return db.PartidoDao().getAllPartidosCompletos()
            .map { partidos: List<PartidoCompleto> ->
                val partidosGrupo = partidos.filter {
                    it.equipoLocal.grupo == grupo
                }
                calcularClasificacion(partidosGrupo)
            }
    }

    /**
     * Cálculo de la clasificación
     * (Le pasaremos a la función los partidos de un grupo)
     */
    private fun calcularClasificacion(partidos: List<PartidoCompleto>): List<ClasificacionEquipo> {

        /* Creamos un Map en el que la clave será el idEquipo y el valor el conjunto de sus
        estadísticas
         */
        val tabla = mutableMapOf<String, Estadisticas>()

        /* Para cada partido calculamos las estadísticas del equipo local y visitante
        y las sumamos a las que tenemos guardadas en su tabla
        (para el primer partido crearemos la tabla con getOrPut
         */
        for (p in partidos) {

            val gl = p.partido.golesLocal
            val gv = p.partido.golesVisitante

            val local = tabla.getOrPut(p.equipoLocal.idEquipo) {
                Estadisticas(p.equipoLocal)
            }

            val visitante = tabla.getOrPut(p.equipoVisitante.idEquipo) {
                Estadisticas(p.equipoVisitante)
            }

            // Solo contamos los parámetros si el partido ya tiene resultado
            if (gl != null && gv != null) {
                local.pj++
                visitante.pj++

                local.dg += gl - gv
                visitante.dg += gv - gl

                /* Utilizamos goles a favor para ordenar la clasificación si los equipos
                empatan en puntos y en diferencia de goles
                 */
                local.gf += gl
                visitante.gf += gv

                when {
                    gl > gv -> local.puntos += 3
                    gl < gv -> visitante.puntos += 3
                    else -> {
                        local.puntos += 1
                        visitante.puntos += 1
                    }
                }
            }
        }

        /* Devolvemos las estadísticas en un objeto de la clase ClasificacionEquipo.
        La clasificación está ordenada por puntos y en caso de empate por mayor diferencia
        de goles
         */
        return tabla.values.map {
            ClasificacionEquipo(
                equipo = it.equipo,
                pj = it.pj,
                puntos = it.puntos,
                diferencia = it.dg,
                golesFavor = it.gf
            )
        }
        .sortedWith(
            compareByDescending<ClasificacionEquipo> { it.puntos }
                .thenByDescending { it.diferencia }
                .thenByDescending { it.golesFavor }
        )
    }

    /**
     * Clase auxiliar para calcular las estadisticas
     */
    private data class Estadisticas(
        val equipo: EquipoEntity,
        var pj: Int = 0,
        var puntos: Int = 0,
        var dg: Int = 0,
        var gf: Int = 0
    )

    /**
     * Métodos de lectura
     **/
    fun getEquipos() = db.EquipoDao().getAllEquipos()
    fun getPartidos() = db.PartidoDao().getAllPartidos()
    fun getSedes() = db.SedeDao().getAllSedes()
    fun getFases() = db.FaseDao().getAllFases()
    fun getPartidosCompletosPorJornada(jornada: Int) =
        db.PartidoDao().getPartidosCompletosPorJornada(jornada)
    fun getPartidosPorFase(fase: String) =
        db.PartidoDao().getPartidosPorFase(fase)
}