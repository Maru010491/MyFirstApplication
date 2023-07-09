package com.example.myapplication2.domain

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.myapplication2.MainRepository
import com.example.myapplication2.data.PreferenceProvider
import com.example.myapplication2.data.entity.TmdbResultsDto
import com.example.myapplication2.data.TmdbApi
import com.example.myapplication2.data.entity.Film
import com.example.myapplication2.utils.API
import com.example.myapplication2.utils.Converter

import com.example.myapplication2.viewmodel.HomeFragmentViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Interactor(
    private val repo: MainRepository, private val retrofitService:
    TmdbApi, private val preferences: PreferenceProvider
) {
    val scope: CoroutineScope = CoroutineScope(Dispatchers.IO)
    var progressBarState = Channel<Boolean>(Channel.CONFLATED)

    fun getFilmsFromApi(page: Int) {
        //Показываем ProgressBar
        scope.launch {
            progressBarState.send(true)
        }

            retrofitService.getFilms(
                getDefaultCategoryFromPreferences(), API.KEY, "ru-RU", page
            ).enqueue(object : Callback<TmdbResultsDto> {
            override fun onResponse(
                call: Call<TmdbResultsDto>, response: Response<TmdbResultsDto>
            ) {
                val list = Converter.convertApiListToDTOList(response.body()?.tmdbFilms)
                scope.launch {
                    repo.putToDb(list)
                    progressBarState.send(false)
                }
            }

            override fun onFailure(call: Call<TmdbResultsDto>, t: Throwable) {
                Log.e("HomeFragmentViewModel", "error: ${t.message}")
                scope.launch {
                    progressBarState.send(false)
                }
            }
        })
    }
    fun saveDefaultCategoryToPreferences(category: String) {
        preferences.saveDefaultCategory(category)
    }

    fun getDefaultCategoryFromPreferences() = preferences.getDefaultCategory()

    fun getFilmsFromDB(): Flow<List<Film>> = repo.getAllFromDB()
}