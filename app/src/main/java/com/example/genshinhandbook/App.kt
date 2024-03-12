package com.example.genshinhandbook

import android.app.Application
import com.example.genshinhandbook.di.AppComponent
import com.example.genshinhandbook.di.DaggerAppComponent
import com.example.genshinhandbook.di.module.AppModule

class App: Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()

    }

    fun getAppComponent(): AppComponent{
        return appComponent
    }

}