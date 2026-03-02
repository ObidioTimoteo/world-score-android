package com.example.worldscore2026.data.remote.api

import com.example.worldscore2026.data.remote.dto.*
import retrofit2.http.GET

interface ApiService {

    @GET("data/paises.json")
    suspend fun getPaises(): List<PaisDto>

    @GET("data/sedes.json")
    suspend fun getSedes(): List<SedeDto>

    @GET("data/fases.json")
    suspend fun getFases(): List<FaseDto>

    @GET("data/equipos.json")
    suspend fun getEquipos(): List<EquipoDto>

    @GET("data/partidos/grupos/jornada1.json")
    suspend fun getPartidosJornada1(): List<PartidoDto>

}