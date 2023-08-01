package com.example.myapplication2.di.modules

import android.content.Context
import androidx.room.Room
import com.example.myapplication2.MainRepository
import com.example.myapplication2.data.dao.FilmDao
import com.example.myapplication2.data.db.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module

class DatabaseModule {
    @Singleton
    @Provides

    fun provideFilmDao(context: Context) =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "film_db"
        ).build().filmDao()

    @Provides
    @Singleton

    fun provideRepository(filmDao: FilmDao) = MainRepository(filmDao)

}