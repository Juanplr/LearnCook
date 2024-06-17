package com.example.learncook

import android.os.Bundle
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.learncook.modelo.LearnCookDB
import com.example.learncook.poko.Receta

class CrearRecetaActivity : AppCompatActivity() {
    private lateinit var closeButton: ImageView
    private lateinit var recipeTitle: EditText
    private lateinit var ingredients: AutoCompleteTextView
    private lateinit var ingredientQuantity: EditText
    private lateinit var description: EditText
    private lateinit var createButton: Button
    private var idUsuario: Int = -1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_crear_receta)

        // Obtener el ID del usuario desde el Intent
        idUsuario = intent.getIntExtra("ID_USUARIO", -1)

        // Verifica si el ID del usuario es válido
        if (idUsuario == -1) {
            Toast.makeText(this, "Error al obtener el ID del usuario", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicializar los elementos UI
        closeButton = findViewById(R.id.close_button)
        recipeTitle = findViewById(R.id.recipe_title)
        ingredients = findViewById(R.id.ingredients)
        ingredientQuantity = findViewById(R.id.ingredient_quantity)
        description = findViewById(R.id.description)
        createButton = findViewById(R.id.create_button)

        // Configurar listener para el botón cerrar
        closeButton.setOnClickListener {
            finish()
        }

        // Configurar listener para el botón crear
        createButton.setOnClickListener {
            createRecipe()
        }
    }


    private fun createRecipe() {
        // Validar entradas
        val title = recipeTitle.text.toString().trim()
        val ingredient = ingredients.text.toString().trim()
        val quantity = ingredientQuantity.text.toString().trim()
        val desc = description.text.toString().trim()

        if (title.isEmpty() || ingredient.isEmpty() || quantity.isEmpty() || desc.isEmpty()) {
            Toast.makeText(this, "Por favor, rellena todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        // Crear instancia de receta
        val receta = Receta(
            id = 0,
            nombre = title,
            tiempo = "Tiempo de la receta",
            presupuesto = quantity.toInt(),
            descripcion = desc,
            idUsuario = idUsuario
        )

        // Insertar receta en la base de datos
        val db = LearnCookDB(this@CrearRecetaActivity)
        val resultado = db.agregarReceta(receta)

        if (resultado != -1L) {
            Toast.makeText(this, "Receta creada", Toast.LENGTH_SHORT).show()
            finish()
        } else {
            Toast.makeText(this, "Error al crear la receta", Toast.LENGTH_SHORT).show()
        }
    }

}
