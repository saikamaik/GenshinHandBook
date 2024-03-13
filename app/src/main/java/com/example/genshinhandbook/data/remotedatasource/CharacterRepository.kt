package com.example.genshinhandbook.data.remotedatasource

import com.example.genshinhandbook.presentation.entity.CharacterDTO
import io.reactivex.rxjava3.core.Single

interface CharacterRepository {

    fun getAllCharacters(): Single<List<CharacterDTO>>

    fun getOneCharacterData(id: String): Single<CharacterDTO>

    fun getAllCharactersName(): Single<List<String>>

}