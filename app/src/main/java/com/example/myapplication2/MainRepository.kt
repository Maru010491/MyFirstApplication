package com.example.myapplication2

import com.example.myapplication2.data.dao.FilmDao
import com.example.myapplication2.data.entity.Film
import io.reactivex.rxjava3.core.Observable

class MainRepository(private val filmDao: FilmDao) {

    fun putToDb(films: List<Film>) {
        filmDao.insertAll(films)
    }

    fun getAllFromDB(): Observable<List<Film>> = filmDao.getCachedFilms()
}
