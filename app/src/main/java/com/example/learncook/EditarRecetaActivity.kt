package com.example.learncook

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.learncook.modelo.LearnCookDB
import com.example.learncook.poko.Receta

class EditarRecetaActivity : AppCompatActivity() {
    private lateinit var recipeNameEditText: EditText
    private lateinit var recipeIngredientesEditText: EditText
    private lateinit var recipeCantidadEditText: EditText
    private lateinit var recipeBudgetEditText: EditText
    private lateinit var recipeDescriptionEditText: EditText
    private lateinit var editButton: Button
    private lateinit var closeButton: ImageView

    private var recetaId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_receta)

        // Inicializar las vistas
        recipeNameEditText = findViewById(R.id.recipe_title)
        recipeIngredientesEditText = findViewById(R.id.ingredients)
        recipeCantidadEditText = findViewById(R.id.ingredient_quantity)
        recipeDescriptionEditText = findViewById(R.id.description)
        editButton = findViewById(R.id.edit_button)
        closeButton = findViewById(R.id.close_button)

        // Obtener el ID de la receta de la intención
        recetaId = intent.getIntExtra("RECETA_ID", -1)

        // Cargar los datos de la receta
        if (recetaId != -1) {
            loadRecipeData(recetaId)
        }

        // Configurar el botón de cerrar
        closeButton.setOnClickListener {
            finish()
        }

        // Configurar el botón de editar
        editButton.setOnClickListener {
            editRecipe()
        }
    }

    private fun loadRecipeData(recetaId: Int) {
        val db = LearnCookDB(this)
        val receta = db.getRecetaById(recetaId)
        receta?.let {
            recipeNameEditText.setText(it.nombre)
            recipeCantidadEditText.setText(it.tiempo)
            recipeBudgetEditText.setText(it.presupuesto.toString())
            recipeDescriptionEditText.setText(it.descripcion)
        }
    }

    private fun editRecipe() {
        val name = recipeNameEditText.text.toString()
        val time = recipeCantidadEditText.text.toString()
        val budget = recipeBudgetEditText.text.toString().toIntOrNull() ?: 0
        val description = recipeDescriptionEditText.text.toString()

        if (name.isNotEmpty() && time.isNotEmpty() && description.isNotEmpty()) {
            val updatedReceta = Receta(
                id = recetaId,
                nombre = name,
                tiempo = time,
                presupuesto = budget,
                descripcion = description,
                idUsuario = 0 // Aquí debes colocar el ID del usuario correspondiente
            )

            val db = LearnCookDB(this)
            val result = db.actualizarReceta(updatedReceta)
            if (result > 0) {
                Toast.makeText(this, "Receta actualizada", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Error al actualizar la receta", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
        }
    }
}
