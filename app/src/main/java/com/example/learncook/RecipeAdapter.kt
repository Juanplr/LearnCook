package com.example.learncook

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.learncook.poko.Receta

class RecipeAdapter(
    private var recipes: List<Receta>,
    private val onDeleteClick: (Receta) -> Unit
) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    inner class RecipeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivRecipeIcon: ImageView = view.findViewById(R.id.ivRecipeIcon)
        val tvRecipeName: TextView = view.findViewById(R.id.tvRecipeName)
        val tvRecipeDescription: TextView = view.findViewById(R.id.tvRecipeDescription)
        val btnDelete: ImageButton = view.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recipe, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val receta = recipes[position]
        holder.tvRecipeName.text = receta.nombre
        holder.tvRecipeDescription.text = receta.descripcion
        holder.btnDelete.setOnClickListener { onDeleteClick(receta) }
    }

    override fun getItemCount() = recipes.size

    fun updateRecipes(newRecipes: List<Receta>) {
        recipes = newRecipes
        notifyDataSetChanged()
    }
}
