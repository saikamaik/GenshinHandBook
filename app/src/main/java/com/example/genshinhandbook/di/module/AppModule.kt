package com.example.genshinhandbook.di.module

import android.content.Context
import com.example.genshinhandbook.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(
    private val app: App
) {

    @Provides
    @Singleton
    fun provideApp(): App = app

    @Singleton
    @Provides
    fun provideAppContext(): Context {
        return app.applicationContext
    }

}