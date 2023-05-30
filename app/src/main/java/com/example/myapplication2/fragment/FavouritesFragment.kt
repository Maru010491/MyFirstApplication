package com.example.myapplication2.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication2.AnimationHelper
import com.example.myapplication2.Film
import com.example.myapplication2.FilmListRecyclerAdapter
import com.example.myapplication2.R

class FavouritesFragment : Fragment(){

    private val favouritesList: List<Film> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_favourites, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val favoritesList = mutableListOf<Film>()

        favoritesList.addAll(favouritesList)

        val favouritesFragmentRoot = view.findViewById<View>(R.id.favourites_fragment_root)
        AnimationHelper.AnimationHelper.performFragmentCircularRevealAnimation(favouritesFragmentRoot, requireActivity(),2)

    }
}