package com.example.myapplication2.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication2.data.entity.Film
import io.reactivex.rxjava3.core.Observable

@Dao
interface FilmDao {

    @Query("SELECT * FROM cached_films")
    fun getCachedFilms(): Observable<List<Film>>

    //Кладем списком в БД, в случае конфликта, перезаписываем
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<Film>)
}
