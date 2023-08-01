package com.example.myapplication2.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.view.View
import com.example.myapplication2.utils.AnimationHelper
import com.example.myapplication2.databinding.FragmentSelectionsBinding

class SelectionsFragment : Fragment() {

        private lateinit var binding: FragmentSelectionsBinding

        override fun onCreateView(

            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View {
            binding = FragmentSelectionsBinding.inflate(
                inflater, container, false
            )
            return binding.root
        }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            AnimationHelper.AnimationHelper.performFragmentCircularRevealAnimation(
               binding.selectionsFragmentRoot, requireActivity(), 4)

    }
}
