package com.example.worldscore2026.ui.partidos.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.worldscore2026.ui.partidos.JornadaFragment

class JornadaPagerAdapter (fragment: Fragment) : FragmentStateAdapter(fragment) {
    private val jornadas = listOf(
        "J1",
        "J2",
        "J3",
        "1/16",
        "1/8",
        "1/4",
        "SF",
        "F"
    )

    override fun getItemCount(): Int = jornadas.size

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> JornadaFragment.newInstanceJornada(1)
            1 -> JornadaFragment.newInstanceJornada(2)
            2 -> JornadaFragment.newInstanceJornada(3)
            3 -> JornadaFragment.newInstanceFase("r16")
            4 -> JornadaFragment.newInstanceFase("r8")
            5 -> JornadaFragment.newInstanceFase("r4")
            6 -> JornadaFragment.newInstanceFase("sf")
            7 -> JornadaFragment.newInstanceFase("final")

            else -> JornadaFragment.newInstanceJornada(1)
        }
    }

    fun getTitle(position: Int): String {
        return jornadas[position]
    }
}