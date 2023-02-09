package com.example.myapplication2.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.myapplication2.Film
import com.example.myapplication2.R

class DetailsFragment : Fragment() {

    private lateinit var poster: AppCompatImageView
    private lateinit var toolbar: Toolbar
    private lateinit var description: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_details, container, false)

        poster = view.findViewById(R.id.details_poster)
        toolbar = view.findViewById(R.id.details_toolbar)
        description = view.findViewById(R.id.details_description)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val film = arguments?.get("film") as Film
        poster.setImageResource(film.poster)
        toolbar.title = film.title
        description.text = film.description
    }
}