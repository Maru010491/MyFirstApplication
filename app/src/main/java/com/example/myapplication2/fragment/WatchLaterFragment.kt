package com.example.myapplication2.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication2.AnimationHelper
import com.example.myapplication2.R

class WatchLaterFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_watch_later, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val watch_later_fragment_root = view.findViewById<View>(R.id.watch_later_fragment_root)
        AnimationHelper.AnimationHelper.performFragmentCircularRevealAnimation(watch_later_fragment_root, requireActivity(), 3)
    }
}