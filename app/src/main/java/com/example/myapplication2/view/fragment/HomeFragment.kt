package com.example.myapplication2.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication2.utils.AnimationHelper
import com.example.myapplication2.FilmListRecyclerAdapter
import com.example.myapplication2.MainActivity
import com.example.myapplication2.OnItemClickListener
import com.example.myapplication2.data.entity.Film
import com.example.myapplication2.view.rv.adapters.TopSpacingItemDecoration
import com.example.myapplication2.databinding.FragmentHomeBinding
import com.example.myapplication2.utils.AutoDisposable
import com.example.myapplication2.utils.addTo
import com.example.myapplication2.viewmodel.HomeFragmentViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableOnSubscribe
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Locale
import java.util.concurrent.TimeUnit

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val viewModel by lazy {
        ViewModelProvider.NewInstanceFactory().create(
            HomeFragmentViewModel::class.java
        )
    }
    private lateinit var filmsAdapter: FilmListRecyclerAdapter

    private val itemClickListener = object : OnItemClickListener {
        override fun click(film: Film) {
            (requireActivity() as MainActivity).launchDetailsFragment(film)
        }
    }

    private val autoDisposable = AutoDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        autoDisposable.bindTo(lifecycle)
    }

   override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
   ): View {
       binding = FragmentHomeBinding.inflate(
           inflater, container,  false
       )
       return binding.root
   }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        filmsAdapter = FilmListRecyclerAdapter(itemClickListener)


        viewModel.filmsListData
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { list ->
                filmsAdapter.addItems(list)
            }
            .addTo(autoDisposable)

        viewModel.showProgressBar
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                binding.progressBar.isVisible = it
            }
            .addTo(autoDisposable)
    }

    override fun onStop() {
        super.onStop()

        fun initPullToRefresh() {
            binding.pullToRefresh.setOnRefreshListener {
                filmsAdapter.items.clear()
                viewModel.getFilms()
                binding.pullToRefresh.isRefreshing = false
            }
        }
        initPullToRefresh()

        initRecycler()
        Observable.create(ObservableOnSubscribe<String> { subscriber ->
            //Вешаем слушатель на клавиатуру
            binding.searchView.setOnQueryTextListener(object :
            //Вызывается на ввод символов
                SearchView.OnQueryTextListener {
                override fun onQueryTextChange(newText: String): Boolean {
                    filmsAdapter.items.clear()
                    subscriber.onNext(newText)
                    return false
                }
                //Вызывается по нажатию кнопки "Поиск"
                override fun onQueryTextSubmit(query: String): Boolean {
                    subscriber.onNext(query)
                    return false
                }
            })
        })
            .subscribeOn(Schedulers.io())
            .map {
                it.toLowerCase(Locale.getDefault()).trim()
            }
            .debounce(800, TimeUnit.MILLISECONDS)
            .filter {
                //Если в поиске пустое поле, возвращаем список фильмов по умолчанию
                viewModel.getFilms()
                it.isNotBlank()
            }
            .flatMap {
                viewModel.getSearchResult(it)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onError = {
                    Toast.makeText(requireContext(), "Что-то пошло не так", Toast.LENGTH_SHORT).show()
                },
                onNext = {
                    filmsAdapter.addItems(it)
                }
            )
            .addTo(autoDisposable)
    }
    private fun initRecycler() {
        binding.mainRecycler.apply {
            adapter = filmsAdapter
            layoutManager = LinearLayoutManager(requireActivity())
            val decorator = TopSpacingItemDecoration(8)
            addItemDecoration(decorator)
        }
    }
    }




