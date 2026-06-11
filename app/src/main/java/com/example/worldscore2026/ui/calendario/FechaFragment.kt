package com.example.worldscore2026.ui.calendario

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.worldscore2026.R
import com.example.worldscore2026.ui.partidos.adapter.PartidoAdapter
import com.example.worldscore2026.ui.viewmodel.WorldScoreViewModel
import kotlinx.coroutines.launch

class FechaFragment : Fragment() {

    private lateinit var viewModel: WorldScoreViewModel
    private lateinit var adapter: PartidoAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recycler = view.findViewById<RecyclerView>(R.id.recyclerPartidos)

        adapter = PartidoAdapter()

        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = adapter

        viewModel = ViewModelProvider(requireActivity())
            .get(WorldScoreViewModel::class.java)

        val fecha = arguments?.getString("fecha") ?: return

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getPartidosPorFecha(fecha).collect {
                adapter.submitList(it)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_jornada, container, false)
    }

    companion object {

        fun newInstance(fecha: String): FechaFragment {
            val fragment = FechaFragment()

            val args = Bundle()
            args.putString("fecha", fecha)

            fragment.arguments = args

            return fragment
        }
    }
}