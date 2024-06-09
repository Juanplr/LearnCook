package com.example.learncook

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.learncook.databinding.ActivityRecuperarContrasenaBinding
import com.example.learncook.utilidades.Email
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecuperarContrasenaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecuperarContrasenaBinding
    private val emailUtil = Email()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecuperarContrasenaBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnEnviarCorreo.setOnClickListener {
            if (validarDatos()) {
                enviarCorreo()
            }
        }
    }

    private fun validarDatos(): Boolean {
        val correo = binding.etCorreo.text.toString()
        var bandera = false
        if (correo.isNotBlank()) {
            bandera = true
        } else {
            binding.etCorreo.setError("Favor de llenar este campo")
        }
        return bandera
    }

    private fun enviarCorreo() {
        val correo = binding.etCorreo.text.toString()
        val subject = "Password Recovery Code"
        val body = "Your recovery code is: 123456"

        // Utilizando una coroutine para enviar el correo electrónico
        CoroutineScope(Dispatchers.Main).launch {
            val exitoEnvio = withContext(Dispatchers.IO) {
                emailUtil.sendEmail(correo, subject, body)
            }

            if (exitoEnvio) {
                binding.prueba.visibility = View.VISIBLE
            } else {
                // Manejar caso de fallo en el envío
                Log.e("Envio", "Error al enviar el correo electrónico")
                // Puedes mostrar un mensaje de error al usuario si es necesario
            }
        }
    }
}
