package com.example.myapplication2.view.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication2.utils.AnimationHelper
import com.example.myapplication2.Film
import com.example.myapplication2.FilmListRecyclerAdapter
import com.example.myapplication2.MainActivity
import com.example.myapplication2.OnItemClickListener
import com.example.myapplication2.view.rv.adapters.TopSpacingItemDecoration
import com.example.myapplication2.databinding.FragmentHomeBinding
import com.example.myapplication2.viewmodel.HomeFragmentViewModel
import java.util.Locale

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val viewModel by lazy {
        ViewModelProvider.NewInstanceFactory().create(
            HomeFragmentViewModel::class.java
        )
    }

    private var filmsDataBase = listOf<Film>()
        set(value) {
            if (field == value) return
            field = value
            filmsAdapter.addItems(field)
        }

    private lateinit var filmsAdapter: FilmListRecyclerAdapter

    private val itemClickListener = object : OnItemClickListener {
        override fun click(film: Film) {
            (requireActivity() as MainActivity).launchDetailsFragment(film)
        }
    }

   override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
   ): View {
       binding = FragmentHomeBinding.inflate(
           inflater, container, false
       )
       return binding.root
   }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.filmsListLiveData.observe(viewLifecycleOwner) {
            filmsDataBase = it
        }

        AnimationHelper.AnimationHelper
            .performFragmentCircularRevealAnimation(
                binding.homeFragmentRoot,
                requireActivity(),
                1
            )

        filmsAdapter = FilmListRecyclerAdapter(itemClickListener)
        filmsAdapter.addItems(filmsDataBase)
        binding.searchView.setOnClickListener {
            binding.searchView.isIconified = false
        }

        binding.searchView.setOnQueryTextListener(
                    object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }
            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.isEmpty()) {
                    filmsAdapter.addItems(filmsDataBase)
                    return true
                }
                val result = filmsDataBase.filter {
                    it.title.toLowerCase(Locale.getDefault())
                        .contains(newText.toLowerCase(Locale.getDefault()))
                }
                filmsAdapter.addItems(result)
                return true
            }
        })
        initRecycler()

    }

    private fun initRecycler() {
        binding.mainRecycler.apply {
            adapter = filmsAdapter
            layoutManager = LinearLayoutManager(requireActivity())
            val decorator = TopSpacingItemDecoration(8)
            addItemDecoration(decorator)
        }
        filmsAdapter.addItems(filmsDataBase)
    }
}