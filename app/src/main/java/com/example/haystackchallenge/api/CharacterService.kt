package com.example.haystackchallenge.api

import com.example.haystackchallenge.domain.model.CharactersResponse
import retrofit2.Response
import retrofit2.http.GET

interface CharacterService {

    @GET("character")
    suspend fun getAllCharacters(): Response<CharactersResponse>

}
