package com.example.worldscore2026.ui.partidos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.worldscore2026.R
import com.example.worldscore2026.data.local.entity.EquipoEntity
import com.example.worldscore2026.data.local.entity.PartidoEntity

class PartidoAdapter : RecyclerView.Adapter<PartidoAdapter.ViewHolder>() {

    private var partidos: List<PartidoEntity> = emptyList()

    fun submitList(lista: List<PartidoEntity>) {
        partidos = lista
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val equipoLocal: TextView = view.findViewById(R.id.txtEquipoLocal)
        val equipoVisitante: TextView = view.findViewById(R.id.txtEquipoVisitante)
        val marcador: TextView = view.findViewById(R.id.txtMarcador)
        val fecha: TextView = view.findViewById(R.id.txtFecha)
        val hora: TextView = view.findViewById(R.id.txtHora)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_partido, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = partidos.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val partido = partidos[position]

        holder.equipoLocal.text = partido.idEquipoLocal
        holder.equipoVisitante.text = partido.idEquipoVisitante

        holder.marcador.text = "${partido.golesLocal} - ${partido.golesVisitante}"

        holder.fecha.text = partido.fecha
        holder.hora.text = partido.hora
    }
}