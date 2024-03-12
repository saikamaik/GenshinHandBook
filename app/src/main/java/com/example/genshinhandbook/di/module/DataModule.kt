package com.example.genshinhandbook.di.module

import com.example.genshinhandbook.data.remotedatasource.ApiService
import com.example.genshinhandbook.data.remotedatasource.CharacterRepository
import dagger.Module
import dagger.Provides

@Module(includes = [ApiModule::class])
class DataModule {

    @Provides
    fun provideCharacterRepository(apiService: ApiService): CharacterRepository {
        return CharacterRepository(apiService)
    }

}