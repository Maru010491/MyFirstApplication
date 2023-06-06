package com.example.myapplication2

import android.app.Application
import com.example.myapplication2.di.AppComponent
import com.example.myapplication2.di.DaggerAppComponent
import com.example.myapplication2.di.modules.DatabaseModule
import com.example.myapplication2.di.modules.DomainModule
import com.example.myapplication2.di.modules.RemoteModule

class App : Application() {
    lateinit var dagger: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        dagger = DaggerAppComponent.builder()
            .remoteModule(RemoteModule())
            .databaseModule(DatabaseModule())
            .domainModule(DomainModule(this))
            .build()
    }

    companion object {
        lateinit var instance: App
            private set





    }


}