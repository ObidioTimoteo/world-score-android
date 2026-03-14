package com.example.worldscore2026.ui.ajustes

import android.content.Context
import android.os.Bundle
import android.os.LocaleList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.fragment.app.Fragment
import com.example.worldscore2026.R
import com.example.worldscore2026.BuildConfig

class AjustesFragment : Fragment() {

    private lateinit var switchModoOscuro: Switch
    private lateinit var radioGrupoTema: RadioGroup
    private lateinit var radioGrupoIdioma: RadioGroup

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
        radioGrupoIdioma = view.findViewById(R.id.radioIdioma)

        /*
        Modifico el texto de la información para incluir la versión de la app
        (recogida desde build.gradle.kts(app)
         */
        val txtInfo = view.findViewById<TextView>(R.id.txtInfo)
        val version = BuildConfig.VERSION_NAME
        txtInfo.text = getString(R.string.information_text) + "\nv $version"

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

        // Cargar idioma guardado
        val language = prefs.getString("language", "es")

        when(language) {
            "es" -> radioGrupoIdioma.check(R.id.radioEsp)
            "en" -> radioGrupoIdioma.check(R.id.radioEng)
        }

        // Listener para cambiar idioma
        radioGrupoIdioma.setOnCheckedChangeListener { _, checkedId ->

            val editor = prefs.edit()

            when(checkedId) {
                R.id.radioEsp -> editor.putString("language", "es")
                R.id.radioEng -> editor.putString("language", "en")
            }

            editor.apply()

            // Reiniciar Activity para aplicar el nuevo idioma
            requireActivity().recreate()
        }


    }
}