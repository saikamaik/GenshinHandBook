package com.example.genshinhandbook

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App: Application() {

//    companion object {
//        lateinit var appComponent: AppComponent
//    }
//
//    override fun onCreate() {
//        super.onCreate()
//
//        appComponent = DaggerAppComponent.builder()
//            .appModule(AppModule(this))
//            .build()
//
//    }
//
//    fun getAppComponent(): AppComponent{
//        return appComponent
//    }

}