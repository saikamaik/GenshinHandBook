package com.example.genshinhandbook.di.module

import com.example.genshinhandbook.data.mapper.CharacterMapper
import com.example.genshinhandbook.data.remotedatasource.ApiService
import com.example.genshinhandbook.data.remotedatasource.CharacterRepositoryImpl
import dagger.Module
import dagger.Provides

@Module(includes = [ApiModule::class])
class DataModule {

    @Provides
    fun provideCharacterMapper() : CharacterMapper {
        return CharacterMapper()
    }

    @Provides
    fun provideCharacterRepository(apiService: ApiService, characterMapper: CharacterMapper): CharacterRepositoryImpl {
        return CharacterRepositoryImpl(apiService, characterMapper)
    }

}