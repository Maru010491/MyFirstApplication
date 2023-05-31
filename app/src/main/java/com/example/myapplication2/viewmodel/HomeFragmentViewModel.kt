package com.example.myapplication2.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication2.App
import com.example.myapplication2.Film
import com.example.myapplication2.domain.Interactor

class HomeFragmentViewModel : ViewModel() {
    val filmsListLiveData = MutableLiveData<List<Film>>()
    private lateinit var interactor: Interactor
    init {
         var interactor: Interactor = App.instance.interactor


        val films = interactor.getFilmsDB()
        filmsListLiveData.postValue(films)
    }


}