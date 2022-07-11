package com.example.marvelcompose.ui.screens.characters

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.marvelcompose.MarvelApp
import com.example.marvelcompose.data.entities.Character
import com.example.marvelcompose.data.repositories.CharactersRepository

@ExperimentalFoundationApi
@Composable
fun CharactersScreen(onClick: (Character) -> Unit) {
    var characterState by rememberSaveable { mutableStateOf(emptyList<Character>()) }

    LaunchedEffect(Unit) {
        characterState = CharactersRepository().get()
    }
    CharactersScreen(
        character = characterState,
        onClick = onClick
    )
}

@ExperimentalFoundationApi
@Composable
fun CharactersScreen(character: List<Character>, onClick: (Character) -> Unit) {
    LazyVerticalGrid(
        cells = GridCells.Adaptive(180.dp),
        contentPadding = PaddingValues(4.dp)
    ) {
        items(character) {
            CharacterItem(
                character = it,
                modifier = Modifier.clickable { onClick(it) }
            )
        }
    }
}

@Composable
fun CharacterItem(character: Character, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(8.dp)) {
        Card {
            Image(
                painter = rememberImagePainter(character.thumbnail),
                contentDescription = character.name,
                modifier = Modifier
                    .background(Color.LightGray)
                    .fillMaxWidth()
                    .aspectRatio(1f),
                contentScale = ContentScale.Crop
            )
        }
        Text(
            text = character.name,
            style = MaterialTheme.typography.subtitle1,
            maxLines = 2,
            modifier = Modifier.padding(8.dp, 16.dp)
        )
    }
}