package com.example.learncook

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.learncook.databinding.ActivityBuscarPresupuestoBinding
import com.example.learncook.fragmentos.RecetaFragment

class BuscarPresupuestoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBuscarPresupuestoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBuscarPresupuestoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar el botón de búsqueda
        binding.Buscar.setOnClickListener {
            val presupuesto = binding.edtPresupuesto.text.toString()
            if (presupuesto.isNotEmpty()) {
                buscarRecetasPorPresupuesto(presupuesto.toDouble())
            } else {
                Toast.makeText(this, "Ingrese un presupuesto", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun buscarRecetasPorPresupuesto(presupuesto: Double) {
        Toast.makeText(this, "Buscando recetas por un presupuesto de $presupuesto", Toast.LENGTH_SHORT).show()


        val fragment = RecetaFragment.newInstance()
        supportFragmentManager.beginTransaction()
            .replace(R.id.Buscar_presupuesto, fragment)
            .addToBackStack(null)
            .commit()
    }
}
