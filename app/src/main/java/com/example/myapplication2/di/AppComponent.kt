package com.example.myapplication2.di

import com.example.myapplication2.di.modules.DatabaseModule
import com.example.myapplication2.di.modules.DomainModule
import com.example.myapplication2.di.modules.RemoteModule
import com.example.myapplication2.viewmodel.HomeFragmentViewModel
import com.example.myapplication2.viewmodel.SettingsFragmentViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        RemoteModule::class,
        DatabaseModule::class,
        DomainModule::class
    ]
)
interface AppComponent {
    fun inject(homeFragmentViewModel: HomeFragmentViewModel)
    fun inject(settingsFragmentViewModel: SettingsFragmentViewModel)
}