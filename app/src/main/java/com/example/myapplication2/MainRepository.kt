package com.example.myapplication2


import androidx.lifecycle.LiveData
import com.example.myapplication2.data.dao.FilmDao
import com.example.myapplication2.data.entity.Film
import kotlinx.coroutines.flow.Flow
import java.util.concurrent.Executors

class MainRepository(private val filmDao: FilmDao) {

    fun putToDb(films: List<Film>) {

        filmDao.insertAll(films)
    }

    fun getAllFromDB(): Flow<List<Film>> = filmDao.getCachedFilms()

}