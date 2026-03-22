package com.example.worldscore2026.ui.equipos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.worldscore2026.R
import com.example.worldscore2026.data.local.entity.EquipoEntity
import com.example.worldscore2026.ui.partidos.JornadaFragment
import com.example.worldscore2026.ui.partidos.adapter.PartidoAdapter
import com.example.worldscore2026.ui.viewmodel.WorldScoreViewModel
import com.example.worldscore2026.utils.getTeamName
import kotlinx.coroutines.launch

class EquiposFragment : Fragment() {

    private lateinit var viewModel: WorldScoreViewModel
    private lateinit var adapter: PartidoAdapter

    private var listaEquipos: List<EquipoEntity> = emptyList()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recycler = view.findViewById<RecyclerView>(R.id.recyclerPartidos)
        val spinner = view.findViewById<Spinner>(R.id.spinnerEquipos)

        // Reutilizamos el mismo adapter que en JornadaFragment
        adapter = PartidoAdapter()

        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = adapter

        viewModel = ViewModelProvider(requireActivity())
            .get(WorldScoreViewModel::class.java)

        // Cargamos equipos en el spinner
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.equipos.collect { equipos ->

                /* Quitamos de la lista de equipos el equipo "tbd" (por definir)
                y ordenamos la lista de equipos alfabéticamente
                 */
                listaEquipos = equipos
                    .filter { it.idEquipo != "tbd" }
                    .sortedBy { getTeamName(requireContext(), it.idEquipo) }

                // Traducimos los nombres de los equipos
                val nombres = listaEquipos.map {
                    getTeamName(requireContext(), it.idEquipo)
                }

                val spinnerAdapter = ArrayAdapter(
                    requireContext(),
                    R.layout.spinner_item,      // aplicamos estilo personalizado
                    nombres
                )

                spinnerAdapter.setDropDownViewResource(
                    R.layout.spinner_dropdown_item      // aplicamos estilo personalizado
                )

                spinner.adapter = spinnerAdapter
            }
        }

        // Seleccionar equipo -> filtrar partidos
        spinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {

                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val equipo = listaEquipos[position]

                    observarPartidosEquipo(equipo.idEquipo)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
    }

    private fun observarPartidosEquipo(idEquipo: String) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getPartidosPorEquipo(idEquipo).collect {
                adapter.submitList(it)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_equipos, container, false)
    }

}