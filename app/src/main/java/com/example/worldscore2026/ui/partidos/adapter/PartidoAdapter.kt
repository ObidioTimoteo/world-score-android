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
import com.example.worldscore2026.utils.getTeamName
import com.example.worldscore2026.data.local.relation.PartidoCompleto

class PartidoAdapter : RecyclerView.Adapter<PartidoAdapter.ViewHolder>() {

    private var partidos: List<PartidoCompleto> = emptyList()

    fun submitList(lista: List<PartidoCompleto>) {
        Log.d("ADAPTER", "Lista recibida: ${lista.size}")
        partidos = lista
        notifyDataSetChanged()
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

        val penaltis: TextView = view.findViewById(R.id.txtPenaltis)
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

        // Penaltis
        val penLocal = partido.partido.penaltisLocal
        val penVisitante = partido.partido.penaltisVisitante

        if (penLocal != null && penVisitante != null) {
            holder.penaltis.text = "PEN $penLocal-$penVisitante"
            holder.penaltis.visibility = View.VISIBLE
        } else {
            holder.penaltis.visibility = View.GONE
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

        /* Grupo
        - Escribimos "Grupo" en su idioma y el grupo que sea
        - Si el partido no es de la fase "grupo" escribimos la eliminatoria (1/16, 1/8, etc.)
        */
        val fase = partido.partido.idFase

        if (fase == "grupo") {
            holder.grupo.text = holder.itemView.context.getString(R.string.group_matches) +
                    " ${partido.equipoLocal.grupo}"
        } else {
            holder.grupo.text = when (fase) {
                "r16" -> "1/16"
                "r8" -> "1/8"
                "r4" -> "1/4"
                "sf" -> "SF"
                "tq" -> "3P"
                "final" -> "F"
                else -> ""
            }
        }

        holder.grupo.visibility = View.VISIBLE
    }
}