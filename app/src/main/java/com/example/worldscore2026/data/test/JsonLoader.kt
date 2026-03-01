package com.example.worldscore2026.data.test

import android.content.Context
import com.example.worldscore2026.data.remote.dto.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object JsonLoader {
    fun loadPaises(context: Context): List<PaisDto> {
        val jsonString = context.assets
            .open("paises.json")
            .bufferedReader()
            .use { it.readText() }

        val type = object : TypeToken<List<PaisDto>>() {}.type

        return Gson().fromJson(jsonString, type)
    }

    fun loadSedes(context: Context): List<SedeDto> {
        val jsonString = context.assets
            .open("sedes.json")
            .bufferedReader()
            .use { it.readText() }

        val type = object : TypeToken<List<SedeDto>>() {}.type

        return Gson().fromJson(jsonString, type)
    }

    fun loadFases(context: Context): List<FaseDto> {
        val jsonString = context.assets
            .open("fases.json")
            .bufferedReader()
            .use { it.readText() }

        val type = object : TypeToken<List<FaseDto>>() {}.type

        return Gson().fromJson(jsonString, type)
    }

    fun loadEquipos(context: Context): List<EquipoDto> {
        val jsonString = context.assets
            .open("equipos.json")
            .bufferedReader()
            .use { it.readText() }

        val type = object : TypeToken<List<EquipoDto>>() {}.type

        return Gson().fromJson(jsonString, type)
    }

    fun loadPartidos(context: Context): List<PartidoDto> {
        val jsonString = context.assets
            .open("partidos.json")
            .bufferedReader()
            .use { it.readText() }

        val type = object : TypeToken<List<PartidoDto>>() {}.type

        return Gson().fromJson(jsonString, type)
    }
}