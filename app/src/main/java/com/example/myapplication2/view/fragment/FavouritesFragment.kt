package com.example.myapplication2.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication2.utils.AnimationHelper
import com.example.myapplication2.Film
import com.example.myapplication2.databinding.FragmentFavouritesBinding

class FavouritesFragment : Fragment(){

    private lateinit var binding: FragmentFavouritesBinding

    private val favouritesList: List<Film> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavouritesBinding.inflate(
            inflater, container, false
        )
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val favoritesList = mutableListOf<Film>()
        favoritesList.addAll(favouritesList)
        AnimationHelper.AnimationHelper
            .performFragmentCircularRevealAnimation(
                binding.favouritesFragmentRoot,
                requireActivity(),2
            )
    }
}