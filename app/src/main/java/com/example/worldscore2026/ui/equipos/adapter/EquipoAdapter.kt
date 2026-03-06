package com.example.worldscore2026.ui.equipos.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.worldscore2026.R
import com.example.worldscore2026.data.local.entity.EquipoEntity

class EquipoAdapter : RecyclerView.Adapter<EquipoAdapter.ViewHolder>() {

    private var equipos: List<EquipoEntity> = emptyList()

    fun submitList(lista: List<EquipoEntity>) {
        equipos = lista
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nombre: TextView = view.findViewById(R.id.txtNombre)
        val bandera: ImageView = view.findViewById(R.id.imgBandera)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_equipo, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = equipos.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val equipo = equipos[position]

        holder.nombre.text = equipo.nombre

        Glide.with(holder.itemView.context)
            .load(equipo.banderaUrl)
            .into(holder.bandera)
    }
}