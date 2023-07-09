package com.example.myapplication2.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication2.App
import com.example.myapplication2.data.entity.Film
import com.example.myapplication2.domain.Interactor
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import java.util.concurrent.Executors

import javax.inject.Inject

class HomeFragmentViewModel : ViewModel() {


    @Inject
    lateinit var interactor: Interactor
    val filmsListData: Flow<List<Film>>
    val showProgressBar: Channel<Boolean>

    init {
        App.instance.dagger.inject(this)
        showProgressBar = interactor.progressBarState
        filmsListData = interactor.getFilmsFromDB()
        getFilms()
    }


    fun getFilms() {
        interactor.getFilmsFromApi(1)
    }


}