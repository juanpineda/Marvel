package com.example.marvelcompose.ui.screens.common

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import com.example.marvelcompose.data.entities.MarvelItem

@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun <T : MarvelItem> MarvelItemsListScreen(
    loading: Boolean = false,
    items: List<T>,
    onClick: (T) -> Unit
) {
    MarvelItemList(
        loading = loading,
        items = items,
        onClick = onClick
    )
}

@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun <T : MarvelItem> MarvelItemList(
    loading: Boolean,
    items: List<T>,
    onClick: (T) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (loading)
            CircularProgressIndicator()
        if (items.isNotEmpty())
            LazyVerticalGrid(
                cells = GridCells.Adaptive(180.dp),
                contentPadding = PaddingValues(4.dp),
                modifier = modifier
            ) {
                items(items) {
                    MarvelListItem(
                        marvelItem = it,
                        modifier = Modifier.clickable { onClick(it) }
                    )
                }
            }
    }
}