package com.example.worldscore2026.ui.clasificacion

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
import com.example.worldscore2026.data.local.entity.EquipoEntity
import com.example.worldscore2026.data.model.ClasificacionEquipo
import com.example.worldscore2026.ui.clasificacion.adapter.ClasificacionAdapter
import com.example.worldscore2026.ui.clasificacion.adapter.PartidoSimpleAdapter
import com.example.worldscore2026.ui.partidos.adapter.PartidoAdapter
import com.example.worldscore2026.ui.viewmodel.WorldScoreViewModel
import kotlinx.coroutines.launch

class ClasificacionGrupoFragment : Fragment() {

    private lateinit var viewModel: WorldScoreViewModel
    private lateinit var adapterClasificacion: ClasificacionAdapter
    private lateinit var adapterPartidos: PartidoSimpleAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerClasificacion = view.findViewById<RecyclerView>(R.id.recyclerClasificacion)
        val recyclerPartidos = view.findViewById<RecyclerView>(R.id.recyclerPartidosGrupo)

        // Adapter para la Clasificación
        adapterClasificacion = ClasificacionAdapter()

        recyclerClasificacion.layoutManager = LinearLayoutManager(requireContext())
        recyclerClasificacion.adapter = adapterClasificacion

        // Adapter para los Partidos
        adapterPartidos = PartidoSimpleAdapter()

        recyclerPartidos.layoutManager = LinearLayoutManager(requireContext())
        recyclerPartidos.adapter = adapterPartidos

        // ViewModel
        viewModel = ViewModelProvider(requireActivity())
            .get(WorldScoreViewModel::class.java)

        val grupo = arguments?.getString("grupo") ?: "A"

        // Clasificación
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getClasificacionGrupo(grupo).collect { lista ->

                // Creamos en la clasificación un item 0 para la cabecera
                val header = ClasificacionEquipo(
                    equipo = EquipoEntity(idEquipo = "", nombre = "", banderaUrl = "", grupo = ""),
                    pj = 0,
                    puntos = 0,
                    diferencia = 0,
                    golesFavor = 0,
                    isHeader = true
                )

                // Añadimos a la lista la cabecera
                val listaConHeader = listOf(header) + lista

                adapterClasificacion.submitList(listaConHeader)
            }
        }

        // Partidos del Grupo
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getPartidosPorGrupo(grupo).collect { lista ->
                adapterPartidos.submitList(lista)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_clasificacion_grupo, container, false)
    }

    companion object {
        fun newInstance(grupo: String): ClasificacionGrupoFragment {
            val fragment = ClasificacionGrupoFragment()

            val args = Bundle()
            args.putString("grupo", grupo)

            fragment.arguments = args

            return fragment
        }
    }
}