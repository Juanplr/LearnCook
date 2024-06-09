package com.example.learncook.modelo

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class LearnCookDB(contexto: Context): SQLiteOpenHelper(contexto,NOMBRE_DB,null,VERSION_DB){
    companion object {
        private const val  NOMBRE_DB = "learnCook.bd"
        //tabla usuario
        private const val NOMBRE_TABLA_USUARIO ="usuario"
        private const val COL_ID_USUARIO = "id"
        private const val COL_CORREO = "correo"
        private const val COL_CONTRASENA = "contrasena"
        private const val COL_NOMBRE_USUARIO = "nombreUsuario"
        //tabla Receta
        private const val NOMBRE_TABLA_RECETA ="receta"
        private const val COL_ID_RECETA = "id"
        private const val COL_USUARIO_ID = "usuarioId"
        private const val COL_TIEMPO = "tiempo"
        private const val COL_PRESUPUESTO = "presupuesto"
        private const val COL_PREPARACION = "preparacion"
        //tabla ingrediente
        private const val NOMBRE_TABLA_INGREDIENTE ="ingrediente"
        private const val COL_ID_INGREDIENTE = "id"
        private const val COL_NOMBRE = "nombre"
        private const val COL_PRECIO = "precio"
        //tabla RecetaIngredientes
        private const val NOMBRE_TABLA_RECETAINGREDIENTES ="recetaIngredientes"
        private const val COL_ID_RECETAINGREDIENTES = "id"
        private const val COL_RECETA_ID = "recetaId"
        private const val COL_INGREDIENTE_ID = "ingredienteId"
        private const val COL_CANTIDAD = "cantidad"
        //tabla seguidor
        private const val NOMBRE_TABLA_SEGUIDOR ="seguidor"
        private const val COL_ID_SEGUIDOR = "id"
        private const val COL_USUARIO_ID_SEGUIDOR = "usuarioId"
        private const val COL_SEGUIDO_ID = "seguidoId"
        //tabla calificaciones
        private const val NOMBRE_TABLA_CALIFICACIONES ="calificaciones"
        private const val COL_ID_CALIFICACIONES = "id"
        private const val COL_USUARIO_ID_CALIFICACION = "usuarioId"
        private const val COL_RECETA_ID_CALIFICACION = "recetaId"
        private const val COL_PUNTUACION = "puntuacion"
        private const val COL_COMENTARIO = "comentario"
        private const val  VERSION_DB = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE_USUARIO = ("CREATE TABLE $NOMBRE_TABLA_USUARIO (" +
                "$COL_ID_USUARIO INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COL_CORREO TEXT NOT NULL UNIQUE, " +
                "$COL_CONTRASENA TEXT NOT NULL, " +
                "$COL_NOMBRE_USUARIO TEXT NOT NULL UNIQUE)")

        val CREATE_TABLE_RECETA = ("CREATE TABLE $NOMBRE_TABLA_RECETA (" +
                "$COL_ID_RECETA INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COL_USUARIO_ID INTEGER NOT NULL, " +
                "$COL_TIEMPO TEXT NOT NULL, " +
                "$COL_PRESUPUESTO REAL, " +
                "$COL_PREPARACION TEXT NOT NULL, " +
                "FOREIGN KEY ($COL_USUARIO_ID) REFERENCES $NOMBRE_TABLA_USUARIO($COL_ID_USUARIO))")

        val CREATE_TABLE_INGREDIENTE = ("CREATE TABLE $NOMBRE_TABLA_INGREDIENTE (" +
                "$COL_ID_INGREDIENTE INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COL_NOMBRE TEXT NOT NULL UNIQUE, " +
                "$COL_PRECIO REAL NOT NULL)")

        val CREATE_TABLE_RECETAINGREDIENTES = ("CREATE TABLE $NOMBRE_TABLA_RECETAINGREDIENTES (" +
                "$COL_ID_RECETAINGREDIENTES INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COL_RECETA_ID INTEGER NOT NULL, " +
                "$COL_INGREDIENTE_ID INTEGER NOT NULL, " +
                "$COL_CANTIDAD REAL NOT NULL, " +
                "FOREIGN KEY ($COL_RECETA_ID) REFERENCES $NOMBRE_TABLA_RECETA($COL_ID_RECETA), " +
                "FOREIGN KEY ($COL_INGREDIENTE_ID) REFERENCES $NOMBRE_TABLA_INGREDIENTE($COL_ID_INGREDIENTE), " +
                "UNIQUE ($COL_RECETA_ID, $COL_INGREDIENTE_ID))")

        val CREATE_TABLE_SEGUIDOR = ("CREATE TABLE $NOMBRE_TABLA_SEGUIDOR (" +
                "$COL_ID_SEGUIDOR INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COL_USUARIO_ID_SEGUIDOR INTEGER NOT NULL, " +
                "$COL_SEGUIDO_ID INTEGER NOT NULL, " +
                "FOREIGN KEY ($COL_USUARIO_ID_SEGUIDOR) REFERENCES $NOMBRE_TABLA_USUARIO($COL_ID_USUARIO), " +
                "FOREIGN KEY ($COL_SEGUIDO_ID) REFERENCES $NOMBRE_TABLA_USUARIO($COL_ID_USUARIO), " +
                "UNIQUE ($COL_USUARIO_ID_SEGUIDOR, $COL_SEGUIDO_ID))")

        val CREATE_TABLE_CALIFICACIONES = ("CREATE TABLE $NOMBRE_TABLA_CALIFICACIONES (" +
                "$COL_ID_CALIFICACIONES INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COL_USUARIO_ID_CALIFICACION INTEGER NOT NULL, " +
                "$COL_RECETA_ID_CALIFICACION INTEGER NOT NULL, " +
                "$COL_PUNTUACION INTEGER NOT NULL CHECK ($COL_PUNTUACION BETWEEN 1 AND 5), " +
                "$COL_COMENTARIO TEXT, " +
                "FOREIGN KEY ($COL_USUARIO_ID_CALIFICACION) REFERENCES $NOMBRE_TABLA_USUARIO($COL_ID_USUARIO), " +
                "FOREIGN KEY ($COL_RECETA_ID_CALIFICACION) REFERENCES $NOMBRE_TABLA_RECETA($COL_ID_RECETA), " +
                "UNIQUE ($COL_USUARIO_ID_CALIFICACION, $COL_RECETA_ID_CALIFICACION))")

        db!!.execSQL(CREATE_TABLE_USUARIO)
        db.execSQL(CREATE_TABLE_RECETA)
        db.execSQL(CREATE_TABLE_INGREDIENTE)
        db.execSQL(CREATE_TABLE_RECETAINGREDIENTES)
        db.execSQL(CREATE_TABLE_SEGUIDOR)
        db.execSQL(CREATE_TABLE_CALIFICACIONES)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}