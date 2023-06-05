package com.example.myapplication2.di

import com.example.myapplication2.di.modules.DatabaseModule
import com.example.myapplication2.di.modules.DomainModule
import com.example.myapplication2.di.modules.RemoteModule
import com.example.myapplication2.viewmodel.HomeFragmentViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    //Внедряем все модули, нужные для этого компонента
    modules = [
        RemoteModule::class,
        DatabaseModule::class,
        DomainModule::class
    ]
)
interface AppComponent {
    fun inject(homeFragmentViewModel: HomeFragmentViewModel)
}