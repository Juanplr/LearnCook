package com.example.learncook

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.learncook.modelo.LearnCookDB
import com.example.learncook.poko.Receta

class BorrarRecetaActivity : AppCompatActivity() {

    private lateinit var etSearchRecipe: EditText
    private lateinit var btnSearch: Button
    private lateinit var rvRecipes: RecyclerView
    private lateinit var recipeAdapter: RecipeAdapter

    private var idUsuario: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_borrar_receta)

        idUsuario = intent.getIntExtra("ID_USUARIO", -1)
        if (idUsuario == -1) {
            Toast.makeText(this, "Error al obtener el ID del usuario", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        etSearchRecipe = findViewById(R.id.etSearchRecipe)
        btnSearch = findViewById(R.id.btnSearch)
        rvRecipes = findViewById(R.id.rvRecipes)

        rvRecipes.layoutManager = LinearLayoutManager(this)
        recipeAdapter = RecipeAdapter(emptyList()) { receta -> showDeleteConfirmationDialog(receta) }
        rvRecipes.adapter = recipeAdapter

        btnSearch.setOnClickListener {
            searchRecipes()
        }
    }

    private fun searchRecipes() {
        val db = LearnCookDB(this)
        val recetas = db.buscarRecetasPorUsuario(idUsuario, etSearchRecipe.text.toString().trim())
        recipeAdapter.updateRecipes(recetas)
    }

    private fun showDeleteConfirmationDialog(receta: Receta) {
        AlertDialog.Builder(this)
            .setTitle("Eliminar Receta")
            .setMessage("¿Estás seguro de que deseas eliminar esta receta?")
            .setPositiveButton("Sí") { _, _ ->
                deleteRecipe(receta)
            }
            .setNegativeButton("No", null)
            .show()
    }

    private fun deleteRecipe(receta: Receta) {
        val db = LearnCookDB(this)
        val result = db.eliminarReceta(receta.id)
        if (result > 0) {
            Toast.makeText(this, "Receta eliminada", Toast.LENGTH_SHORT).show()
            searchRecipes()
        } else {
            Toast.makeText(this, "Error al eliminar la receta", Toast.LENGTH_SHORT).show()
        }
    }
}
