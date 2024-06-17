package com.example.learncook.fragmentos

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.learncook.LoginActivity
import com.example.learncook.databinding.FragmentPerfilBinding
import android.widget.Button
import com.example.learncook.EditarPerfilActivity

private const val ARG_ID_USUARIO = "idUsuario"

class PerfilFragment : Fragment() {
    private lateinit var binding: FragmentPerfilBinding
    private var idUsuario: Int = -1
    private lateinit var btnEditarPerfil: Button
    private lateinit var btnBuscarAmigos: Button
    private lateinit var btnEliminarPerfil: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            idUsuario = it.getInt(ARG_ID_USUARIO)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPerfilBinding.inflate(inflater, container, false)
        btnEditarPerfil = binding.btnEditarP
        btnBuscarAmigos = binding.btnBuscarA
        btnEliminarPerfil = binding.btnEliminarP
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCerrarSesion.setOnClickListener {

            val intent = Intent(requireContext(), LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        btnEditarPerfil.setOnClickListener {
            val intent = Intent(requireContext(), EditarPerfilActivity::class.java)
            intent.putExtra("idUsuario", idUsuario)
            startActivity(intent)
        }
        btnBuscarAmigos.setOnClickListener {
            // Acción al hacer clic en el botón Buscar amigos
        }

        btnEliminarPerfil.setOnClickListener {
            // Acción al hacer clic en el botón Eliminar perfil
        }


    }

    companion object {
        @JvmStatic
        fun newInstance(idUsuario: Int) =
            PerfilFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_ID_USUARIO, idUsuario)
                }
            }
    }
}
