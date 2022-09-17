package com.example.haystackchallenge.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.haystackchallenge.domain.usecase.FetchCharactersUseCase
import com.example.haystackchallenge.common.Resource
import com.example.haystackchallenge.domain.model.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCase: FetchCharactersUseCase
) : ViewModel() {

    val characterList: LiveData<Resource<List<Character>>>
        get() = _characterList
    private val _characterList = MutableLiveData<Resource<List<Character>>>()

    fun load() {
        useCase().onEach { result ->
            with(_characterList) {
                when (result) {
                    is Resource.Success -> postValue(result)
                    is Resource.Error -> postValue(Resource.Error(message = "${result.message}"))
                    is Resource.Loading -> postValue(Resource.Loading())
                }
            }
        }.launchIn(viewModelScope)
    }

}