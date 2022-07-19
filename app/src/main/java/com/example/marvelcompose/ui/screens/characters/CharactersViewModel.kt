package com.example.marvelcompose.ui.screens.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelcompose.data.entities.Character
import com.example.marvelcompose.data.repositories.CharactersRepository
import kotlinx.coroutines.launch

class CharactersViewModel : ViewModel() {

    private val _state = MutableLiveData<UiState>()
    val state: LiveData<UiState> get() = _state

    init {
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            _state.value = UiState(items = CharactersRepository.get())
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val items: List<Character> = emptyList()
    )
}