package com.example.myapplication2.view.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication2.data.entity.Film
import com.example.myapplication2.utils.AnimationHelper
import com.example.myapplication2.databinding.FragmentFavouritesBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.*

class FavouritesFragment : Fragment(){

    private lateinit var binding: FragmentFavouritesBinding
    private val favouritesList: List<Film> = emptyList()
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<View>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavouritesBinding.inflate(
            inflater, container, false
        )
        return binding.root
       }

    @SuppressLint("WrongConstant")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val favoritesList = mutableListOf<Film>()
        favoritesList.addAll(favouritesList)

        bottomSheetBehavior = from(binding.bottomView.bottomSheet)

        bottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
//                TODO("Not yet implemented")
            }
            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                // handle onSlide
            }
        })
        bottomSheetBehavior.state = STATE_COLLAPSED

        AnimationHelper.AnimationHelper
            .performFragmentCircularRevealAnimation(
                binding.favouritesFragmentRoot,
                requireActivity(), 2
            )
    }
}
