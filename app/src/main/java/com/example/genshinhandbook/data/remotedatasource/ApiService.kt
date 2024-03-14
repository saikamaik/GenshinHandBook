package com.example.genshinhandbook.data.remotedatasource

import com.example.genshinhandbook.data.model.CharacterDTO
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/characters/all")
    fun getAllDetailedCharacters(): Single<List<CharacterDTO>>

    @GET("/characters/{id}")
    fun getOneCharacterData(
        @Path("id") id: String
    ): Single<CharacterDTO>

    @GET("/characters")
    fun getAllCharactersName(): Single<List<String>>

}