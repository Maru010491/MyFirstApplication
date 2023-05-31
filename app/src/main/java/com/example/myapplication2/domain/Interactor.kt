package com.example.myapplication2.domain

import com.example.myapplication2.Film
import com.example.myapplication2.MainRepository

class Interactor(val repo: MainRepository) {

    fun getFilmsDB(): List<Film> = repo.filmsDataBase
}