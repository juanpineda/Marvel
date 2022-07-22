package com.example.marvelcompose.ui.screens.comics

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.right
import com.example.marvelcompose.data.entities.Character
import com.example.marvelcompose.data.entities.Comic
import com.example.marvelcompose.data.network.entities.Result
import com.example.marvelcompose.data.repositories.ComicsRepository
import com.example.marvelcompose.ui.screens.characters.CharactersViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ComicsViewModel : ViewModel() {

    val state = Comic.Format.values().associateWith { MutableStateFlow(UiState()) }

    data class UiState(
        val loading: Boolean = false,
        val items: Result<List<Comic>> = emptyList<Comic>().right()
    )

    fun formatRequested(format: Comic.Format) {
        val uiState = state.getValue(format)
        if (uiState.value.items.isNotEmpty()) return
        viewModelScope.launch {
            uiState.value = UiState(loading = true)
            uiState.value = UiState(items = ComicsRepository.get(format))
        }
    }
}