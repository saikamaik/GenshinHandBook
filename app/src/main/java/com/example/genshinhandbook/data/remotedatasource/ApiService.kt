package com.example.genshinhandbook.data.remotedatasource

import com.example.genshinhandbook.data.model.Character
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/characters/all")
    fun getAllDetailedCharacters(): Single<List<Character>>

    @GET("/characters/{id}")
    fun getOneCharacterData(
        @Path("id") id: String
    ): Single<Character>

    @GET("/characters")
    fun getAllCharactersName(): Single<List<String>>

}