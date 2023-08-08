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
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
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
    var progressBarState: BehaviorSubject<Boolean> = BehaviorSubject.create()

    fun getFilmsFromApi(page: Int) {
        //Показываем ProgressBar
        progressBarState.onNext(true)

        retrofitService.getFilms(
            getDefaultCategoryFromPreferences(),
            API.KEY, "ru-RU", page
        ).enqueue(object : Callback<TmdbResultsDto> {

            override fun onResponse(
                call: Call<TmdbResultsDto>,
                response: Response<TmdbResultsDto>
            ) {
                Log.e("HomeFragmentViewModel", "LOADING SUCCESS")
                val list = Converter.convertApiListToDTOList(response.body()?.tmdbFilms)
                Log.e("HomeFragmentViewModel", "LOADED LIST: $list")
                Completable.fromSingle<List<Film>> {
                    repo.putToDb(list)
                }
                    .subscribeOn(Schedulers.io())
                    .subscribe()
                progressBarState.onNext(false)
            }

            override fun onFailure(call: Call<TmdbResultsDto>, t: Throwable) {
                Log.e("HomeFragmentViewModel", "error: ${t.message}")
                progressBarState.onNext(false)
            }
        })
    }

    fun getSearchResultFromApi(search: String): Observable<List<Film>> =
        retrofitService.getFilmFromSearch(API.KEY,
        "ru-RU", search, 1
    )
        .map {
            Converter.convertApiListToDTOList(it.tmdbFilms)
        }

    fun saveDefaultCategoryToPreferences(category: String) {
        preferences.saveDefaultCategory(category)
    }

    fun getDefaultCategoryFromPreferences() = preferences.getDefaultCategory()

    fun getFilmsFromDB(): Observable<List<Film>> = repo.getAllFromDB()
}