package com.example.myapplication2.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication2.App
import com.example.myapplication2.data.entity.Film
import com.example.myapplication2.domain.Interactor
import java.util.concurrent.Executors

import javax.inject.Inject

class HomeFragmentViewModel : ViewModel() {
    val showProgressBar: MutableLiveData<Boolean> = MutableLiveData()


    @Inject
    lateinit var interactor: Interactor
    val filmsListLiveData: LiveData<List<Film>>

    init {
        App.instance.dagger.inject(this)
        filmsListLiveData = interactor.getFilmsFromDB()
        getFilms()
    }


    fun getFilms() {
        showProgressBar.postValue(true)
        interactor.getFilmsFromApi(1, object : ApiCallback {
            override fun onSuccess() {
                showProgressBar.postValue(false)
            }

            override fun onFailure() {
                Executors.newSingleThreadExecutor().execute {
                    showProgressBar.postValue(false)
                }
            }
        })
    }

    interface ApiCallback {
        fun onSuccess()
        fun onFailure()
    }


}