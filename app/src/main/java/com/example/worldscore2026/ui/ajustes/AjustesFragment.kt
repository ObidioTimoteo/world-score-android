package com.example.worldscore2026.ui.ajustes

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.Switch
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.example.worldscore2026.R

class AjustesFragment : Fragment() {

    private lateinit var switchModoOscuro: Switch
    private lateinit var radioGrupoTema: RadioGroup

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ajustes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        switchModoOscuro = view.findViewById(R.id.switchModoOscuro)
        radioGrupoTema = view.findViewById(R.id.radioGrupoTema)

        // Para guardar las preferencias
        val prefs = requireActivity().getSharedPreferences("settings", Context.MODE_PRIVATE)

        // Cargar modo oscuro guardado
        val darkMode = prefs.getBoolean("dark_mode", false)
        switchModoOscuro.isChecked = darkMode

        if(darkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        // Listener modo oscuro
        switchModoOscuro.setOnCheckedChangeListener { _, isChecked ->

            prefs.edit().putBoolean("dark_mode", isChecked).apply()

            if(isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }

            // Reiniciar Activity para aplicar el nuevo tema
            requireActivity().recreate()
        }

        // Cargar tema guardado
        val theme = prefs.getString("theme", "default")

        when(theme) {
            "canada" -> radioGrupoTema.check(R.id.radioCanada)
            "usa" -> radioGrupoTema.check(R.id.radioUSA)
            "mexico" -> radioGrupoTema.check(R.id.radioMexico)
            else -> radioGrupoTema.check(R.id.radioDefault)
        }

        // Listener para cambiar tema
        radioGrupoTema.setOnCheckedChangeListener { _, checkedId ->

            val editor = prefs.edit()

            when(checkedId) {
                R.id.radioDefault -> editor.putString("theme", "default")
                R.id.radioCanada -> editor.putString("theme", "canada")
                R.id.radioUSA -> editor.putString("theme", "usa")
                R.id.radioMexico -> editor.putString("theme", "mexico")
            }
            editor.apply()

            // Reiniciar Activity para aplicar el nuevo tema
            requireActivity().recreate()
        }
    }
}