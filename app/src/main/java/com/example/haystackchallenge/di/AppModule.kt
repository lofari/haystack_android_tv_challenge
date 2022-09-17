package com.example.haystackchallenge.di

import com.example.haystackchallenge.common.Constants.BASE_URL
import com.example.haystackchallenge.api.CharacterService
import com.example.haystackchallenge.domain.repository.CharacterRepositoryImpl
import com.example.haystackchallenge.domain.repository.CharactersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideApi(): CharacterService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CharacterService::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(
        service: CharacterService
    ): CharactersRepository {
        return CharacterRepositoryImpl(service)
    }

}
