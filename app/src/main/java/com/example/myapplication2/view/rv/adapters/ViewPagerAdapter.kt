package com.example.myapplication2.view.rv.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication2.R
import com.example.myapplication2.data.PagerItem
import com.example.myapplication2.view.rv.viewholders.PagerViewHolder

class ViewPagerAdapter : RecyclerView.Adapter<PagerViewHolder>() {

    private val items = mutableListOf<PagerItem>()
    override fun getItemCount(): Int = items.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder =
        PagerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_page, parent, false))
    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.bind(items[position])
    }
    fun setItems(list: List<PagerItem>) {
        items.clear()
        items.addAll(list)
    }

}