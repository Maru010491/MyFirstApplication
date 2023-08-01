package com.example.myapplication2.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication2.R
import com.example.myapplication2.data.PagerItem
import com.example.myapplication2.databinding.FragmentSettingsBinding
import com.example.myapplication2.utils.AnimationHelper
import com.example.myapplication2.view.rv.adapters.ViewPagerAdapter
import com.example.myapplication2.viewmodel.SettingsFragmentViewModel

class SettingsFragment : Fragment() {

    private lateinit var binding: FragmentSettingsBinding
    private val viewModel by lazy {
        ViewModelProvider.NewInstanceFactory().create(
            SettingsFragmentViewModel::class.java)
    }
    private val pagerAdapter = ViewPagerAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsBinding.inflate(layoutInflater, container,
            false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewPager2.adapter = pagerAdapter

        val pagerItems = listOf<PagerItem>(
            PagerItem(ContextCompat.getColor(requireActivity(), R.color.purple_200), "Red"),
            PagerItem(ContextCompat.getColor(requireActivity(), R.color.teal_700), "Green"),
            PagerItem(ContextCompat.getColor(requireActivity(), R.color.colorAccent), "Yellow")
        )
        pagerAdapter.setItems(pagerItems)

        AnimationHelper.AnimationHelper.performFragmentCircularRevealAnimation(
           binding.settingsFragmentRoot , requireActivity(), 5
        )

        viewModel.categoryPropertyLifeData.observe(viewLifecycleOwner,
            Observer<String> {

            when(it) {
                POPULAR_CATEGORY -> binding.radioGroup.check(R.id.radio_popular)
                TOP_RATED_CATEGORY -> binding.radioGroup.check(R.id.radio_top_rated)
                UPCOMING_CATEGORY -> binding.radioGroup.check(R.id.radio_upcoming)
                NOW_PLAYING_CATEGORY -> binding.radioGroup.check(R.id.radio_now_playing)
            }
        })

        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId) {
                R.id.radio_popular -> viewModel.putCategoryProperty(POPULAR_CATEGORY)
                R.id.radio_top_rated -> viewModel.putCategoryProperty(TOP_RATED_CATEGORY)
                R.id.radio_upcoming -> viewModel.putCategoryProperty(UPCOMING_CATEGORY)
                R.id.radio_now_playing -> viewModel.putCategoryProperty(NOW_PLAYING_CATEGORY)
            }
        }
    }
    companion object {
        private const val POPULAR_CATEGORY = "popular"
        private const val TOP_RATED_CATEGORY = "top_rated"
        private const val UPCOMING_CATEGORY = "upcoming"
        private const val NOW_PLAYING_CATEGORY = "now_playing"
    }
}