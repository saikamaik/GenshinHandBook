package com.example.genshinhandbook.data.remotedatasource

import com.example.genshinhandbook.presentation.entity.CharacterDTO
import com.example.genshinhandbook.presentation.entity.toCharacterDTO
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : CharacterRepository {

    override fun getAllCharacters(): Single<List<CharacterDTO>> {
        return apiService.getAllDetailedCharacters().map { it ->
            it.map { it.toCharacterDTO() }
        }
    }

    override fun getOneCharacterData(id: String): Single<CharacterDTO> {
        return apiService.getOneCharacterData(id).map { it.toCharacterDTO() }
    }

    override fun getAllCharactersName(): Single<List<String>> {
        return apiService.getAllCharactersName()
    }

}