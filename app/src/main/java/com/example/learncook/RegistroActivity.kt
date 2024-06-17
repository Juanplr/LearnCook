package com.example.learncook

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.learncook.databinding.ActivityLoginBinding
import com.example.learncook.databinding.ActivityRegistroBinding
import com.example.learncook.modelo.LearnCookDB
import com.example.learncook.poko.Usuario

class RegistroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistroBinding
    private lateinit var modelo: LearnCookDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_registro)
        val view = binding.root
        setContentView(view)
        modelo = LearnCookDB(this@RegistroActivity)

        binding.btnRegistrar.setOnClickListener {
            val correo = binding.etCorreo.text.toString()
            val contrasena = binding.etContrasena.text.toString()
            val nombreUsuario = binding.etNombreUsuario.text.toString()

            if (validarCampos(correo, contrasena, nombreUsuario)) {
                val usuario = Usuario(-1, correo, contrasena, nombreUsuario)
                if (!modelo.isCorreo(correo)) {
                    val idUsuario = modelo.agregarUsuario(usuario)
                    if (idUsuario != -1L) {
                        Toast.makeText(this@RegistroActivity, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show()
                        finish() // Cerrar la actividad de registro después de registrar al usuario
                    } else {
                        Toast.makeText(this@RegistroActivity, "Error al registrar usuario", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@RegistroActivity, "El correo electrónico ya está registrado", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun validarCampos(correo: String, contrasena: String, nombreUsuario: String): Boolean {
        var valido = true
        if (correo.isEmpty()) {
            binding.etCorreo.error = "Campo requerido"
            valido = false
        }
        if (contrasena.isEmpty()) {
            binding.etContrasena.error = "Campo requerido"
            valido = false
        }
        if (nombreUsuario.isEmpty()) {
            binding.etNombreUsuario.error = "Campo requerido"
            valido = false
        }
        return valido
    }

}