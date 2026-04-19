package com.example.worldscore2026.ui.clasificacion.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.worldscore2026.R
import com.example.worldscore2026.data.local.relation.PartidoCompleto
import com.example.worldscore2026.utils.getTeamName

class PartidoSimpleAdapter : RecyclerView.Adapter<PartidoSimpleAdapter.ViewHolder>() {

    private var partidos: List<PartidoCompleto> = emptyList()

    fun submitList(lista: List<PartidoCompleto>) {
        partidos = lista
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val equipoLocal: TextView = view.findViewById(R.id.txtEquipoLocal)
        val equipoVisitante: TextView = view.findViewById(R.id.txtEquipoVisitante)
        val marcador: TextView = view.findViewById(R.id.txtMarcador)

        val imgLocal: ImageView = view.findViewById(R.id.imgLocal)
        val imgVisitante: ImageView = view.findViewById(R.id.imgVisitante)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_partido_simple_card, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount() = partidos.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val partido = partidos[position]
        val context = holder.itemView.context

        // Equipos
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

        // Banderas
        Glide.with(holder.itemView.context)
            .load(partido.equipoLocal.banderaUrl)
            .into(holder.imgLocal)

        Glide.with(holder.itemView.context)
            .load(partido.equipoVisitante.banderaUrl)
            .into(holder.imgVisitante)

    }
}