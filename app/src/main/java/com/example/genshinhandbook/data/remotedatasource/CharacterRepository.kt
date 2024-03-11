package com.example.genshinhandbook.data.remotedatasource

import com.example.genshinhandbook.data.Character
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val apiService: ApiService
) {

    fun getAllCharacters(): Single<List<Character>> {
        return apiService.getAllDetailedCharacters()
    }

    fun getOneCharacterData(id: String): Single<Character> {
        return apiService.getOneCharacterData(id)
    }

    fun getAllCharactersName(): Single<List<String>>{
        return apiService.getAllCharactersName()
    }

}