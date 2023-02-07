package com.example.myapplication2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.Toolbar

class DetailsActivity : AppCompatActivity() {

    private lateinit var poster: AppCompatImageView
    private lateinit var toolbar: Toolbar
    private lateinit var description: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val obj = intent.extras?.get("film")
        val film = obj as Film

        poster = findViewById(R.id.details_poster)
        toolbar = findViewById(R.id.details_toolbar)
        description = findViewById(R.id.details_description)

        poster.setImageResource(film.poster)
        toolbar.title = film.title
        description.text = film.description
    }
}