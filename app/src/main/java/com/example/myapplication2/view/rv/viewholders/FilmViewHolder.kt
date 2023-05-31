package com.example.myapplication2.view.rv.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication2.utils.Film
import com.example.myapplication2.R
import com.example.myapplication2.data.ApiConstants
import com.example.myapplication2.view.customviews.RatingDonutView

class FilmViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val title = itemView.findViewById<TextView>(R.id.title)
    private val poster = itemView.findViewById<ImageView>(R.id.poster)
    private val description = itemView.findViewById<TextView>(R.id.description)
    private val ratingDonut = itemView.findViewById<RatingDonutView>(R.id.rating_donut)


    fun bind(film: Film) {
        title.text = film.title
        description.text = film.description

        ratingDonut.setProgress((film.rating * 10).toInt())

        Glide.with(itemView)
    .load(ApiConstants.IMAGES_URL + "w342" +film.poster)
    .centerCrop()
    .into(poster)

    }

}


