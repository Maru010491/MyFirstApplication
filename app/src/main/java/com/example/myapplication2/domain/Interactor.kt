package com.example.myapplication2.domain

import android.util.Log
import com.example.myapplication2.MainRepository
import com.example.myapplication2.data.PreferenceProvider
import com.example.myapplication2.data.entity.TmdbResultsDto
import com.example.myapplication2.data.TmdbApi
import com.example.myapplication2.utils.API
import com.example.myapplication2.utils.Converter
import com.example.myapplication2.utils.Film
import com.example.myapplication2.viewmodel.HomeFragmentViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Interactor(
    private val repo: MainRepository, private val retrofitService:
    TmdbApi, private val preferences: PreferenceProvider
) {

    fun getFilmsFromApi(
        page: Int, callback: HomeFragmentViewModel.ApiCallback
    ) {
            retrofitService.getFilms(
                getDefaultCategoryFromPreferences(), API.KEY, "ru-RU", page
            ).enqueue(object : Callback<TmdbResultsDto> {
            override fun onResponse(
                call: Call<TmdbResultsDto>, response: Response<TmdbResultsDto>
            ) {
                val list = Converter.convertApiListToDTOList(response.body()?.tmdbFilms)
                //Кладем фильмы в бд
                list.forEach {
                    repo.putToDb(film = it)
                }
                callback.onSuccess(list)
            }

            override fun onFailure(call: Call<TmdbResultsDto>, t: Throwable) {
                Log.e("HomeFragmentViewModel", "error: ${t.message}")
                callback.onFailure()
            }
        })
    }
    fun saveDefaultCategoryToPreferences(category: String) {
        preferences.saveDefaultCategory(category)
    }

    fun getDefaultCategoryFromPreferences() = preferences.getDefaultCategory()

    fun getFilmsFromDB(): List<Film> = repo.getAllFromDB()
}