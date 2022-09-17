package com.example.haystackchallenge.domain.repository

import com.example.haystackchallenge.api.CharacterService
import com.example.haystackchallenge.domain.model.Character
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CharacterRepositoryImpl @Inject constructor(
    private val api: CharacterService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : CharactersRepository {

    override suspend fun getCharacters(): List<Character> =
        withContext(dispatcher) {
            val charactersResponse = api.getAllCharacters()
            if (charactersResponse.isSuccessful) {
                return@withContext charactersResponse.body()!!.results
            }
            return@withContext emptyList()
        }

}
