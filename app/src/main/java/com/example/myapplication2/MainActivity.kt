package com.example.myapplication2

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication2.view.fragment.DetailsFragment
import com.example.myapplication2.view.fragment.HomeFragment
import androidx.fragment.app.Fragment
import com.example.myapplication2.data.entity.Film
import com.example.myapplication2.databinding.ActivityMainBinding
import com.example.myapplication2.view.fragment.FavouritesFragment
import com.example.myapplication2.view.fragment.SelectionsFragment
import com.example.myapplication2.view.fragment.SettingsFragment
import com.example.myapplication2.view.fragment.WatchLaterFragment

class MainActivity() : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListeners()

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_placeholder, HomeFragment())
            .addToBackStack(null)
            .commit()

        initNavigation()
    }
    fun launchDetailsFragment(film: Film) {
        val bundle = Bundle()
        bundle.putParcelable("film", film)
        val fragment = DetailsFragment()
        fragment.arguments = bundle

        supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.fragment_placeholder, fragment)
            .commit()
    }

    private fun setListeners() {
        binding.topAppBar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menu_main_setting -> {
                    Toast.makeText(this, "Настройки", Toast.LENGTH_SHORT).show()
                    true
                }

                else -> false
            }
        }
    }

    private fun initNavigation() {
        binding.bottomNavigation.setOnNavigationItemSelectedListener {

                when (it.itemId) {
                    R.id.home -> {
                        val tag = "home"
                        val fragment = checkFragmentExistence(tag)
                        changeFragment( fragment ?: HomeFragment(), tag)
                        true
                    }
                    R.id.app_bar_favourites -> {
                        val tag = "favourites"
                        val fragment = checkFragmentExistence(tag)
                       changeFragment( fragment?: FavouritesFragment(), tag)
                        true
                    }
                    R.id.app_bar_late -> {
                        val tag = "watch_later"
                        val fragment = checkFragmentExistence(tag)
                        Log.d("SUPERTEST", "app_bar_late")
                        changeFragment( fragment?: WatchLaterFragment(), tag)
                        true
                    }
                    R.id.app_bar_podborka -> {
                        val tag = "selections"
                        val fragment = checkFragmentExistence(tag)
                        changeFragment( fragment?: SelectionsFragment(), tag)
                        true
                    }
                    R.id.settings -> {
                        val tag = "settings"
                        val fragment = checkFragmentExistence(tag)
                        changeFragment( fragment?: SettingsFragment(), tag)
                        true
                    }
                    else -> false
                }
            }
        }
        private fun checkFragmentExistence(tag: String): Fragment? =
            supportFragmentManager.findFragmentByTag(tag)

        private fun changeFragment(fragment: Fragment, tag: String) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_placeholder, fragment, tag)
                .addToBackStack(null)
                .commit()
        }
}
