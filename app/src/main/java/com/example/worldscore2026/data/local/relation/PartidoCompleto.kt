package com.example.worldscore2026.data.local.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.example.worldscore2026.data.local.entity.EquipoEntity
import com.example.worldscore2026.data.local.entity.PartidoEntity
import com.example.worldscore2026.data.local.entity.SedeEntity

/**
 * Todos los datos de Partidos
 */
class PartidoCompleto (
    @Embedded
    val partido: PartidoEntity,

    @Relation(
        parentColumn = "idEquipoLocal",
        entityColumn = "idEquipo"
    )
    val equipoLocal: EquipoEntity,

    @Relation(
        parentColumn = "idEquipoVisitante",
        entityColumn = "idEquipo"
    )
    val equipoVisitante: EquipoEntity,

    @Relation(
        parentColumn = "idSede",
        entityColumn = "idSede"
    )
    val sede: SedeEntity
)