package com.example.myapplication2.view.rv.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication2.R
import com.example.myapplication2.data.ApiConstants
import com.example.myapplication2.data.entity.Film
import com.example.myapplication2.databinding.FilmItemBinding
import com.example.myapplication2.view.customviews.RatingDonutView

class FilmViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val filmItemBinding = FilmItemBinding.bind(itemView)
    //Привязываем view из layout к переменным
    private val title = filmItemBinding.title
    private val poster = filmItemBinding.poster
    private val description = filmItemBinding.description
    //Вот здесь мы находим в верстке наш прогресс бар для рейтинга
    private val ratingDonut = filmItemBinding.ratingDonut


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


