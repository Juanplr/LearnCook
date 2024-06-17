package com.example.learncook.fragmentos

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.learncook.BuscarIngredienteActivity
import com.example.learncook.BuscarPresupuestoActivity
import com.example.learncook.CrearRecetaActivity
import com.example.learncook.databinding.FragmentRecetaBinding


class RecetaFragment : Fragment() {
    private lateinit var binding: FragmentRecetaBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Usar View Binding para inflar el layout
        binding = FragmentRecetaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configurar los listeners para los botones
        binding.btnBuscarP.setOnClickListener {
            val intent = Intent(requireContext(), BuscarPresupuestoActivity::class.java)
            startActivity(intent)
        }

        binding.btnBuscarI.setOnClickListener {
            val intent = Intent(requireContext(), BuscarIngredienteActivity::class.java)
            startActivity(intent)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = RecetaFragment()

    }
}
