package com.example.learncook

import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.learncook.databinding.ActivityRecuperarContrasenaBinding
import com.example.learncook.utilidades.Email
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random

class RecuperarContrasenaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecuperarContrasenaBinding
    private val emailUtil = Email()
    private lateinit var sensorManager: SensorManager
    private lateinit var sensor: Sensor
    private lateinit var progressBar: ProgressBar
    private var codigo: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecuperarContrasenaBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        progressBar = binding.progressBar

        binding.btnEnviarCorreo.setOnClickListener {
            if (validarDatosCorreo()) {
                enviarCorreo()
            }
        }
        binding.btnEnviarCodigo.setOnClickListener {
            if (validarDatosCodigo()) {
                val codigoDeEntrada = binding.etCodigo.text.toString().toIntOrNull()
                if (codigoDeEntrada != null && esIgualElCodigo(codigo, codigoDeEntrada)) {
                    binding.etCodigo.visibility = View.GONE
                    binding.btnEnviarCodigo.visibility = View.GONE
                    binding.etContrasena.visibility = View.VISIBLE
                    binding.btnRestaurarContrasena.visibility = View.VISIBLE
                    Toast.makeText(this@RecuperarContrasenaActivity, "Escribe una nueva contraseña", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this@RecuperarContrasenaActivity, "Error: el código no es correcto", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun validarDatosCorreo(): Boolean {
        val correo = binding.etCorreo.text.toString()
        return if (correo.isNotBlank() && correo.contains("@")) {
            true
        } else {
            binding.etCorreo.error = "Favor de ingresar un correo válido"
            false
        }
    }

    private fun enviarCorreo() {
        val correo = binding.etCorreo.text.toString()
        val subject = "Código de Recuperación"
        codigo = generarCodigo()
        val body = "Tu código de recuperación es: $codigo"

        progressBar.visibility = View.VISIBLE

        CoroutineScope(Dispatchers.Main).launch {
            val exitoEnvio = withContext(Dispatchers.IO) {
                emailUtil.sendEmail(correo, subject, body)
            }

            progressBar.visibility = View.GONE

            if (exitoEnvio) {
                binding.etCorreo.visibility = View.GONE
                binding.btnEnviarCorreo.visibility = View.GONE
                binding.etCodigo.visibility = View.VISIBLE
                binding.btnEnviarCodigo.visibility = View.VISIBLE
                Toast.makeText(this@RecuperarContrasenaActivity, "Se envió un código a tu correo electrónico", Toast.LENGTH_LONG).show()
            } else {
                Log.e("Envio", "Error al enviar el correo electrónico")
                Toast.makeText(this@RecuperarContrasenaActivity, "Error al enviar el correo", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun generarCodigo(): Int {
        return Random.nextInt(1000, 10000)
    }

    private fun validarDatosCodigo(): Boolean {
        val codigo = binding.etCodigo.text.toString()
        return if (codigo.length == 4 && codigo.all { it.isDigit() }) {
            true
        } else {
            binding.etCodigo.error = "El código debe tener 4 dígitos"
            false
        }
    }

    private fun esIgualElCodigo(codigo: Int, codigoDeEntrada: Int): Boolean {
        return codigo == codigoDeEntrada
    }
}
