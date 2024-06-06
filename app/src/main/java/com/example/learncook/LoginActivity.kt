package com.example.learncook

import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.learncook.databinding.ActivityLoginBinding
import com.example.learncook.modelo.LearnCookDB


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var modelo: LearnCookDB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val  view = binding.root
        setContentView(view)
        modelo = LearnCookDB(this@LoginActivity)
        var bd: SQLiteDatabase = modelo.writableDatabase
    }
}
