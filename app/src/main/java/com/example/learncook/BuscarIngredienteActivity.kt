package com.example.learncook

import android.graphics.Color
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.learncook.databinding.ActivityBuscarIngredienteBinding
import com.example.learncook.fragmentos.RecetaFragment

class BuscarIngredienteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBuscarIngredienteBinding
    private val ingredientes = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBuscarIngredienteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar el Spinner con una lista de ingredientes de ejemplo
        val ingredientesList = listOf("Tomate", "Cebolla", "Ajo", "Pimiento", "Carne", "Pescado")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, ingredientesList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerIngredientes.adapter = adapter

        // Configurar el botón de agregar ingrediente
        binding.btnAgregar.setOnClickListener {
            val ingrediente = binding.spinnerIngredientes.selectedItem.toString()
            agregarIngrediente(ingrediente)
        }

        // Configurar el botón de búsqueda
        binding.Buscar.setOnClickListener {
            if (ingredientes.isNotEmpty()) {
                buscarRecetasPorIngredientes(ingredientes)
            } else {
                Toast.makeText(this, "Agregue al menos un ingrediente", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun agregarIngrediente(ingrediente: String) {
        ingredientes.add(ingrediente)
        val row = TableRow(this).apply {
            val textView = TextView(this@BuscarIngredienteActivity).apply {
                text = ingrediente
                setPadding(16, 16, 16, 16)
                setTextColor(Color.WHITE)
            }
            addView(textView)
        }
        binding.tblIngtediente.addView(row)
    }

    private fun buscarRecetasPorIngredientes(ingredientes: List<String>) {
        // Aquí implementarás la lógica para buscar recetas por ingredientes
        // Por ahora, simplemente muestra un mensaje
        Toast.makeText(this, "Buscando recetas con los ingredientes: ${ingredientes.joinToString()}", Toast.LENGTH_SHORT).show()

        // Ejemplo: Navegar a un fragmento (RecetaFragment) con los ingredientes
        val fragment = RecetaFragment.newInstance()
        supportFragmentManager.beginTransaction()
            .replace(R.id.Buscar_ingrediente, fragment)
            .addToBackStack(null)
            .commit()
    }
}
