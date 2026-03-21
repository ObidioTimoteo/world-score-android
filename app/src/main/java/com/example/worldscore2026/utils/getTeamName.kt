package com.example.worldscore2026.utils

/*
     Función para convertir el id del equipo en el nombre del equipo en el idioma seleccionado.
     Por ejemplo: "esp" -> "team_esp" -> R.string.team_esp -> "España / Spain"
*/

fun getTeamName(context: android.content.Context, teamId: String): String {
    val resourceName = "team_$teamId"

    val resId = context.resources.getIdentifier(
        resourceName,
        "string",
        context.packageName
    )

    return if (resId != 0) {
        context.getString(resId)
    } else {
        teamId
    }
}