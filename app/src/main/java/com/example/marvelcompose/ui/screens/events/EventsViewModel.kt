package com.example.marvelcompose.ui.screens.events

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.right
import com.example.marvelcompose.data.entities.Comic
import com.example.marvelcompose.data.entities.Event
import com.example.marvelcompose.data.network.entities.Result
import com.example.marvelcompose.data.repositories.EventsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class EventsViewModel : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            _state.value = UiState(items = EventsRepository.get())
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val items: Result<List<Event>> = emptyList<Event>().right()
    )
}