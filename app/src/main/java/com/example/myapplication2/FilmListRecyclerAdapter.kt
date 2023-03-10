package com.example.myapplication2

import FilmViewHolder
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class FilmListRecyclerAdapter(
    private val clickListener: OnItemClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items = mutableListOf<Film>()

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val inflater = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.film_item, parent, false)
        return FilmViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is FilmViewHolder -> {
                val item = items[position]
                holder.bind(item)
                holder.itemView.setOnClickListener {
                    clickListener.click(item)
                }
            }
        }
    }

    fun addItems(list: List<Film>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }
}

interface OnItemClickListener {
    fun click(film: Film)
}