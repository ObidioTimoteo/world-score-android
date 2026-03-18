package com.example.worldscore2026.ui.clasificacion.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.worldscore2026.R
import com.example.worldscore2026.data.local.relation.PartidoCompleto
import com.example.worldscore2026.data.model.ClasificacionEquipo

class ClasificacionAdapter : RecyclerView.Adapter<ClasificacionAdapter.ViewHolder>() {

    private var equipos: List<ClasificacionEquipo> = emptyList()

    fun submitList(lista: List<ClasificacionEquipo>) {
        equipos = lista
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtPos: TextView = view.findViewById(R.id.txtPos)
        val txtEquipo: TextView = view.findViewById(R.id.txtEquipo)
        val txtPJ: TextView = view.findViewById(R.id.txtPJ)
        val txtPtsDg: TextView = view.findViewById(R.id.txtPtosDg)

        val imgBandera: ImageView = view.findViewById(R.id.imgBandera)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_clasificacion_equipo, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount() = equipos.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = equipos[position]

        // Posicion
        holder.txtPos.text = (position + 1).toString()

        // Nombre equipo
        holder.txtEquipo.text = item.equipo.nombre

        // Partidos jugados
        holder.txtPJ.text = item.pj.toString()

        // Puntos + diferencia goles
        val dg = if (item.diferencia >= 0) {
            "+${item.diferencia}"
        } else {
            "${item.diferencia}"
        }

        holder.txtPtsDg.text = "${item.puntos}$dg"

        // Banderas
        Glide.with(holder.itemView.context)
            .load(item.equipo.banderaUrl)
            .into(holder.imgBandera)
    }
}