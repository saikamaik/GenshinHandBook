package com.example.genshinhandbook.data.remotedatasource

import com.example.genshinhandbook.data.model.Character
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val apiService: ApiService
): CharacterRepository {

    override fun getAllCharacters(): Single<List<Character>> {
        return apiService.getAllDetailedCharacters()
    }

    override fun getOneCharacterData(id: String): Single<Character> {
        return apiService.getOneCharacterData(id)
    }

    override fun getAllCharactersName(): Single<List<String>>{
        return apiService.getAllCharactersName()
    }

}