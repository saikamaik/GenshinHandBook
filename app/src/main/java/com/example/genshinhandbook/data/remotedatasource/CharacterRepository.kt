package com.example.genshinhandbook.data.remotedatasource

import com.example.genshinhandbook.data.model.Character
import io.reactivex.rxjava3.core.Single

interface CharacterRepository {

    fun getAllCharacters(): Single<List<Character>>

    fun getOneCharacterData(id: String): Single<Character>

    fun getAllCharactersName(): Single<List<String>>

}