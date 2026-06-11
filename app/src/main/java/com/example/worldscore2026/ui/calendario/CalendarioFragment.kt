package com.example.worldscore2026.ui.calendario

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.example.worldscore2026.R
import com.example.worldscore2026.ui.calendario.adapter.CalendarioPagerAdapter
import com.example.worldscore2026.ui.viewmodel.WorldScoreViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.launch

class CalendarioFragment : Fragment() {

    private lateinit var viewModel: WorldScoreViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewPager = view.findViewById<ViewPager2>(R.id.viewPager)
        val tabLayout = view.findViewById<TabLayout>(R.id.tabLayout)

        val adapter = CalendarioPagerAdapter(this)

        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = adapter.getTitle(position)
        }.attach()

        viewModel = ViewModelProvider(requireActivity())
            .get(WorldScoreViewModel::class.java)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getFechaActual().collect { fechaEntity ->

                fechaEntity?.let {

                    val posicion =
                        adapter.getPositionByDate(it.fechaActual)

                    if (posicion >= 0) {
                        viewPager.setCurrentItem(posicion, false)
                    }
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_partidos, container, false)
    }
}