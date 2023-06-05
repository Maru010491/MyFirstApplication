package com.example.myapplication2.di.modules

import com.example.myapplication2.MainRepository
import com.example.myapplication2.data.TmdbApi
import com.example.myapplication2.domain.Interactor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule {
    @Singleton
    @Provides
    fun provideInteractor(repository: MainRepository, tmdbApi: TmdbApi) =
        Interactor(repo = repository, retrofitService = tmdbApi)
}