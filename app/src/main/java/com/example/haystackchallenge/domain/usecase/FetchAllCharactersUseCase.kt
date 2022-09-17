package com.example.haystackchallenge.domain.usecase

import com.example.haystackchallenge.common.Resource
import com.example.haystackchallenge.domain.model.Character
import com.example.haystackchallenge.domain.repository.CharactersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class FetchCharactersUseCase @Inject constructor(
    private val repository: CharactersRepository
) {
    operator fun invoke(): Flow<Resource<List<Character>>> = flow {
        try {
            emit(Resource.Loading())
            val charactersResponse = repository.getCharacters()
            emit(Resource.Success(charactersResponse))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("An unexpected error occurred"))
        }
    }
}