package com.example.learncook.fragmentos

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.learncook.CrearRecetaActivity
import com.example.learncook.databinding.FragmentRecetaBinding

private const val ARG_PARAM1 = "idUsuario"

class RecetaFragment : Fragment() {
    private lateinit var binding: FragmentRecetaBinding
    private var idUsuario: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            idUsuario = it.getInt(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecetaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.botonReceta.setOnClickListener {
            val intent = Intent(requireContext(), CrearRecetaActivity::class.java).apply {
                putExtra("ID_USUARIO", idUsuario)
            }
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(idUsuario: Int) =
            RecetaFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, idUsuario)
                }
            }
    }
}
