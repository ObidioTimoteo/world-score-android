package com.example.worldscore2026.ui.calendario.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.worldscore2026.ui.calendario.FechaFragment
import com.example.worldscore2026.ui.partidos.JornadaFragment

class CalendarioPagerAdapter (fragment: Fragment) : FragmentStateAdapter(fragment) {
    private val fechas = listOf(
        "11/06/26",
        "12/06/26",
        "13/06/26",
        "14/06/26",
        "15/06/26",
        "16/06/26",
        "17/06/26",
        "18/06/26",
        "19/06/26",
        "20/06/26",
        "21/06/26",
        "22/06/26",
        "23/06/26",
        "24/06/26",
        "25/06/26",
        "26/06/26",
        "27/06/26",
        "28/06/26",
        "29/06/26",
        "30/06/26",
        "01/07/26",
        "02/07/26",
        "03/07/26",
        "04/07/26",
        "05/07/26",
        "06/07/26",
        "07/07/26",
        "08/07/26",
        "09/07/26",
        "10/07/26",
        "11/07/26",
        "12/07/26",
        "13/07/26",
        "14/07/26",
        "15/07/26",
        "16/07/26",
        "17/07/26",
        "18/07/26",
        "19/07/26"
    )

    override fun getItemCount(): Int = fechas.size

    override fun createFragment(position: Int): Fragment {
        return FechaFragment.newInstance(fechas[position])
    }

    fun getTitle(position: Int): String {
        return fechas[position].substring(0, 5)
    }

    fun getPositionByDate(fecha: String): Int {
        return fechas.indexOf(fecha)
    }
}