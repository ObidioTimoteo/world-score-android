package com.example.worldscore2026.ui.partidos.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.worldscore2026.R
import com.example.worldscore2026.data.local.entity.PartidoEntity
import com.example.worldscore2026.data.local.relation.PartidoCompleto

class PartidoAdapter : RecyclerView.Adapter<PartidoAdapter.ViewHolder>() {

    private var partidos: List<PartidoCompleto> = emptyList()

    fun submitList(lista: List<PartidoCompleto>) {
        Log.d("ADAPTER", "Lista recibida: ${lista.size}")
        partidos = lista
        notifyDataSetChanged()
    }

    /*
     Función para convertir el id del equipo en el nombre del equipo en el idioma seleccionado.
     Por ejemplo: "esp" -> "team_esp" -> R.string.team_esp -> "España / Spain"
     */
    private fun getTeamName(context: android.content.Context, teamId: String): String {
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

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val equipoLocal: TextView = view.findViewById(R.id.txtEquipoLocal)
        val equipoVisitante: TextView = view.findViewById(R.id.txtEquipoVisitante)
        val marcador: TextView = view.findViewById(R.id.txtMarcador)
        val fecha: TextView = view.findViewById(R.id.txtFecha)
        val hora: TextView = view.findViewById(R.id.txtHora)

        val imgLocal: ImageView = view.findViewById(R.id.imgLocal)
        val imgVisitante: ImageView = view.findViewById(R.id.imgVisitante)

        val sede: TextView = view.findViewById(R.id.txtSede)

        val grupo: TextView = view.findViewById(R.id.txtGrupo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_partido_card, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount() = partidos.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val partido = partidos[position]

        // Equipos
        val context = holder.itemView.context

        val nombreLocal =
            if (partido.equipoLocal.idEquipo == "tbd")
                partido.partido.placeholderLocal
            else
                getTeamName(context, partido.equipoLocal.idEquipo)

        val nombreVisitante =
            if (partido.equipoVisitante.idEquipo == "tbd")
                partido.partido.placeholderVisitante
            else
                getTeamName(context, partido.equipoVisitante.idEquipo)

        holder.equipoLocal.text = nombreLocal
        holder.equipoVisitante.text = nombreVisitante

        // Marcador
        val golesLocal = partido.partido.golesLocal
        val golesVisitante = partido.partido.golesVisitante

        holder.marcador.text =
            if (golesLocal == null && golesVisitante == null) {
                " vs "
            } else {
                "${golesLocal} - ${golesVisitante}"
            }

        // Fecha y hora
        holder.fecha.text = "📅  ${partido.partido.fecha}"
        holder.hora.text = "🕒  ${partido.partido.hora}"

        // Banderas
        Glide.with(holder.itemView.context)
            .load(partido.equipoLocal.banderaUrl)
            .into(holder.imgLocal)

        Glide.with(holder.itemView.context)
            .load(partido.equipoVisitante.banderaUrl)
            .into(holder.imgVisitante)

        // Sede
        holder.sede.text = "${partido.sede.nombre} (${partido.sede.idPais})"

        // Grupo (Escribimos "Grupo" en su idioma)
        holder.grupo.text = holder.itemView.context.getString(R.string.group) +
                " ${partido.equipoLocal.grupo}"
    }
}