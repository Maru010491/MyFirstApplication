package com.example.myapplication2.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.view.View
import com.example.myapplication2.AnimationHelper
import com.example.myapplication2.R

    class SelectionsFragment : Fragment() {

        override fun onCreateView(

            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.fragment_selections, container, false)
        }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       val selections_fragment_root = view.findViewById<View>(R.id.selections_fragment_root)
            AnimationHelper.AnimationHelper.performFragmentCircularRevealAnimation(
                selections_fragment_root, requireActivity(), 4)

    }
}
