package com.example.marvelcompose.ui.screens.comics

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelcompose.data.entities.Comic
import com.example.marvelcompose.data.repositories.ComicsRepository
import com.example.marvelcompose.ui.navigation.NavArg
import kotlinx.coroutines.launch

class ComicsDetailViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {

    private val id = savedStateHandle.get<Int>(NavArg.ItemId.key) ?: throw IllegalStateException()
    var state by mutableStateOf(UiState())
        private set

    init {
        viewModelScope.launch {
            state = UiState(loading = true)
            state = UiState(item = ComicsRepository.find(id))
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val item: Comic? = null
    )
}