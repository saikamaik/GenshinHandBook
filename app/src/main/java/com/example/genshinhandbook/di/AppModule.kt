package com.example.genshinhandbook.di

import com.example.genshinhandbook.data.remotedatasource.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://genshin.jmp.blue/"

@Module
@InstallIn(SingletonComponent::class)
class AppModule
{

    @Singleton
    @Provides
    fun provideRetrofit() : Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()

    @Singleton
    @Provides
    fun provideMainService(retrofit : Retrofit) : ApiService {
        return retrofit.create(ApiService::class.java)
    }

//    @Singleton
//    @Provides
//    fun provideAppContext(): Context {
//        return application.applicationContext
//    }

}