package com.example.worldscore2026.ui.clasificacion.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.worldscore2026.R
import com.example.worldscore2026.ui.clasificacion.ClasificacionGrupoFragment

class ClasificacionPagerAdapter (fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val context = fragment.requireContext()

    private val grupos = listOf(
        "A","B","C","D","E","F","G","H","I","J","K","L"
    )

    override fun getItemCount(): Int = grupos.size

    override fun createFragment(position: Int): Fragment {
        return ClasificacionGrupoFragment.newInstance(grupos[position])
    }

    fun getTitle(position: Int): String {
        return context.getString(R.string.group_standings, grupos[position])
    }
}