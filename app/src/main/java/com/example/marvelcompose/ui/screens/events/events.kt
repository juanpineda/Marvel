package com.example.marvelcompose.ui.screens.events

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.annotation.ExperimentalCoilApi
import com.example.marvelcompose.data.entities.Event
import com.example.marvelcompose.ui.screens.common.MarvelItemDetailScreen
import com.example.marvelcompose.ui.screens.common.MarvelItemsListScreen

@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun EventsScreen(onClick: (Event) -> Unit, eventsViewModel: EventsViewModel = viewModel()) {
    MarvelItemsListScreen(
        loading = eventsViewModel.state.loading,
        items = eventsViewModel.state.items,
        onClick = onClick
    )
}

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun EventDetailScreen(eventsDetailViewModel: EventsDetailViewModel = viewModel()) {
    MarvelItemDetailScreen(
        loading = eventsDetailViewModel.state.loading,
        marvelItem = eventsDetailViewModel.state.item
    )
}