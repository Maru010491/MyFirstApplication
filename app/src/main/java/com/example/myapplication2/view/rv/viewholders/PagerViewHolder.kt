package com.example.myapplication2.view.rv.viewholders

import android.content.ClipData.Item
import android.view.View
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication2.R
import com.example.myapplication2.data.PagerItem

class PagerViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val textView = itemView.findViewById<TextView>(R.id.textView)
    private val container = itemView.findViewById<ConstraintLayout>(R.id.container)

    fun bind(item: PagerItem) {
        textView.text = item.text
        container.setBackgroundColor(item.color)
    }
}