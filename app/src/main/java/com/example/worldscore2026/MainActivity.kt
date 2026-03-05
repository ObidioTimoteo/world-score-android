package com.example.worldscore2026

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.worldscore2026.data.local.database.WorldScoreApp
import com.example.worldscore2026.data.remote.api.RetrofitInstance
import com.example.worldscore2026.data.repository.WorldScoreRepository
import com.example.worldscore2026.ui.adapter.EquipoAdapter
import com.example.worldscore2026.ui.viewmodel.WorldScoreViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: WorldScoreViewModel
    private lateinit var adapter: EquipoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = (application as WorldScoreApp).database
        val repository = WorldScoreRepository(RetrofitInstance.api, db)

        viewModel = WorldScoreViewModel(repository)

        adapter = EquipoAdapter()

        val recycler = findViewById<RecyclerView>(R.id.recyclerEquipos)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter

        lifecycleScope.launch {
            viewModel.equipos.collect {
                Log.d("EQUIPOS", "Total equipos: ${it.size}")
                adapter.submitList(it)
            }
        }
    }
}