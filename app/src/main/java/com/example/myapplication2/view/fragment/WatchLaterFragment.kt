package com.example.myapplication2.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication2.utils.AnimationHelper
import com.example.myapplication2.databinding.FragmentWatchLaterBinding

class WatchLaterFragment: Fragment() {

    private lateinit var binding: FragmentWatchLaterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWatchLaterBinding.inflate(
            inflater, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AnimationHelper.AnimationHelper.performFragmentCircularRevealAnimation(
            binding.watchLaterFragmentRoot, requireActivity(), 3
        )
    }
}