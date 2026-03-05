package com.example.worldscore2026.ui.partidos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.worldscore2026.R
import com.example.worldscore2026.data.local.database.WorldScoreApp
import com.example.worldscore2026.data.remote.api.RetrofitInstance
import com.example.worldscore2026.data.repository.WorldScoreRepository
import com.example.worldscore2026.ui.viewmodel.WorldScoreViewModel
import com.example.worldscore2026.ui.viewmodel.WorldScoreViewModelFactory
import kotlinx.coroutines.launch

class PartidosFragment : Fragment() {

    private lateinit var viewModel: WorldScoreViewModel
    private lateinit var adapter: PartidoAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recycler = view.findViewById<RecyclerView>(R.id.recyclerPartidos)

        adapter = PartidoAdapter()

        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = adapter

        val app = requireActivity().application as WorldScoreApp

        val repository = WorldScoreRepository(
            RetrofitInstance.api,
            app.database
        )

        val factory = WorldScoreViewModelFactory(repository)

        viewModel = ViewModelProvider(requireActivity(), factory)
            .get(WorldScoreViewModel::class.java)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.partidos.collect {
                adapter.submitList(it)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_partidos, container, false)
    }
}