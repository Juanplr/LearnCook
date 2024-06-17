package com.example.learncook.adaptadores

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.learncook.R
import com.example.learncook.interfaces.ListenerRecycleCalificacion
import com.example.learncook.poko.Calificacion

class CalificacionAdapter (val calificaciones: List<Calificacion>, val listener : ListenerRecycleCalificacion): RecyclerView.Adapter<CalificacionAdapter.ViewHolderCalificacion>(){

    class ViewHolderCalificacion(itemView: View): RecyclerView.ViewHolder(itemView){
        val tvNombreUsuario: TextView = itemView.findViewById(R.id.tv_nombre_usuario)
        val tvCalificacion: TextView = itemView.findViewById(R.id.tv_calificacion)
        val tvComentario: TextView = itemView.findViewById(R.id.tv_comentario)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCalificacion {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_calificaciones,parent,false)

        return ViewHolderCalificacion(itemView)
    }

    override fun getItemCount(): Int {
        return calificaciones.size
    }

    override fun onBindViewHolder(holder: ViewHolderCalificacion, position: Int) {
        val calificacion = calificaciones.get(position)
        holder.tvNombreUsuario.text = ""
        holder.tvCalificacion.text = ""
        holder.tvComentario.text =""
    }
}