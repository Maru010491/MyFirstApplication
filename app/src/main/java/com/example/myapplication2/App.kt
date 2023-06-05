package com.example.myapplication2

import android.app.Application
import com.example.myapplication2.di.AppComponent
import com.example.myapplication2.di.DaggerAppComponent

class App : Application() {
    lateinit var dagger: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        //Создаем компонент
        dagger = DaggerAppComponent.create()
    }

    companion object {
        lateinit var instance: App
            private set





    }


}