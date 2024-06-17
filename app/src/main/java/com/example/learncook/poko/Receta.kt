package com.example.learncook.poko

data class Receta(
    val id: Int,
    val nombre:String, val descripcion: String, val tiempo: String,
    val presupuesto: Int,
    val idUsuario: Int )
