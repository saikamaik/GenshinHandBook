package com.example.genshinhandbook.data.remotedatasource

import com.example.genshinhandbook.data.mapper.CharacterMapper
import com.example.genshinhandbook.presentation.entity.Character
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val characterMapper: CharacterMapper
) : CharacterRepository {

    override fun getAllCharacters(): Single<List<Character>> {
        return apiService.getAllDetailedCharacters(). map { characterDTOList ->
            characterDTOList.map { characterMapper.toEntity(it) }
        }
    }

    override fun getOneCharacterData(id: String): Single<Character> {
        return apiService.getOneCharacterData(id).map { characterMapper.toEntity(it) }
    }

    override fun getAllCharactersName(): Single<List<String>> {
        return apiService.getAllCharactersName()
    }

}