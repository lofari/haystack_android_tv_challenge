package com.example.haystackchallenge.domain.repository

import com.example.haystackchallenge.domain.model.Character

interface CharactersRepository {

    suspend fun getCharacters(): List<Character>

}