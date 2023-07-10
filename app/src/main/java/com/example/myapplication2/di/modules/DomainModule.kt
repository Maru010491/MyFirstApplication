package com.example.myapplication2.di.modules

import android.content.Context
import com.example.myapplication2.MainRepository
import com.example.myapplication2.data.PreferenceProvider
import com.example.myapplication2.data.TmdbApi
import com.example.myapplication2.domain.Interactor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule (val context: Context) {
    @Provides
    fun provideContext() = context

    @Singleton
    @Provides
    //Создаем экземпляр SharedPreferences
    fun providePreferences(context: Context) = PreferenceProvider(context)

    @Singleton
    @Provides
    fun provideInteractor(repository: MainRepository,
            tmdbApi: TmdbApi, preferenceProvider: PreferenceProvider) = Interactor(
        repo = repository, retrofitService = tmdbApi,
        preferences = preferenceProvider)
}