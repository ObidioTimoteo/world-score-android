package com.example.worldscore2026.ui.partidos

import android.os.Bundle
import android.util.Log
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

class JornadaFragment : Fragment() {
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

        val jornada = arguments?.getInt("jornada", -1) ?: -1
        val fase = arguments?.getString("fase")

        /* Si es un partido de la fase de grupos (jornada no será null)
           Si es un partido de eliminitarios jornada será null y leemos por fase
        */
        viewLifecycleOwner.lifecycleScope.launch {
            if (jornada != -1){
                viewModel.getPartidosPorJornada(jornada).collect {
                    adapter.submitList(it)
                }
            } else if (fase != null) {
                viewModel.getPartidosPorFase(fase).collect {
                    adapter.submitList(it)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_jornada, container, false)
    }

    companion object {
        fun newInstanceJornada(jornada: Int): JornadaFragment {
            val fragment = JornadaFragment()

            val args = Bundle()
            args.putInt("jornada", jornada)

            fragment.arguments = args

            return fragment
        }

        fun newInstanceFase(fase: String): JornadaFragment {
            val fragment = JornadaFragment()

            val args = Bundle()
            args.putString("fase", fase)

            fragment.arguments = args

            return fragment
        }
    }
}