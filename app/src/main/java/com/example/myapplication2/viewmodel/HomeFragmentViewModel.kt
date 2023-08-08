package com.example.myapplication2.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.myapplication2.App
import com.example.myapplication2.data.entity.Film
import com.example.myapplication2.domain.Interactor
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject

import javax.inject.Inject

class HomeFragmentViewModel : ViewModel() {

    @Inject
    lateinit var interactor: Interactor

    val filmsListData: Observable<List<Film>>

    val showProgressBar: BehaviorSubject<Boolean>

    init {
        App.instance.dagger.inject(this)
        showProgressBar = interactor.progressBarState
        filmsListData = interactor.getFilmsFromDB()
        getFilms()
    }

    fun getFilms() {
        Log.d("HomeFragment", "loading films")
        interactor.getFilmsFromApi(1)
    }
    fun getSearchResult(search: String) = interactor.getSearchResultFromApi(search)
}
