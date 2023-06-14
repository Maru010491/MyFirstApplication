package com.example.myapplication2


import androidx.lifecycle.LiveData
import com.example.myapplication2.data.dao.FilmDao
import com.example.myapplication2.data.entity.Film
import java.util.concurrent.Executors

class MainRepository(private val filmDao: FilmDao) {

    fun putToDb(films: List<Film>) {
        //Запросы в бд должны быть в отдельном потоке
        Executors.newSingleThreadExecutor().execute {
            filmDao.insertAll(films)
        }
    }

    fun getAllFromDB(): LiveData<List<Film>> =
        filmDao.getCachedFilms()

}