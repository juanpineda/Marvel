package com.example.marvelcompose.ui.screens.charactersdetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.marvelcompose.MarvelApp
import com.example.marvelcompose.data.entities.Character
import com.example.marvelcompose.data.entities.Reference
import com.example.marvelcompose.data.repositories.CharactersRepository

@ExperimentalMaterialApi
@Composable
fun CharacterDetailScreen(id: Int) {
    var characterState by remember { mutableStateOf<Character?>(null) }
    LaunchedEffect(Unit) {
        characterState = CharactersRepository().findCharacters(id)
    }
    characterState?.let { character ->
        CharacterDetailScreen(character)
    }
}

@ExperimentalMaterialApi
@Composable
fun CharacterDetailScreen(character: Character) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        item {
            Header(character)
        }
        section(Icons.Default.Collections,"Series",character.series)
        section(Icons.Default.Event,"Event",character.events)
        section(Icons.Default.Book,"Comic",character.comics)
        section(Icons.Default.Bookmark,"Comic",character.stories)
    }
}

@ExperimentalMaterialApi
fun LazyListScope.section(icon: ImageVector, name: String, items: List<Reference>) {
    item {
        Text(
            text = name,
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(16.dp)
        )
    }
    items(items) {
        ListItem(
            icon = { Icon(icon, contentDescription = null) },
            text = { Text(text = it.name) }
        )
    }
}

    @Composable
    fun Header(character: Character) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = rememberImagePainter(character.thumbnail),
                contentDescription = character.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray)
                    .aspectRatio(1f)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = character.name,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h4,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp, 0.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = character.description,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(16.dp, 0.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }

@ExperimentalMaterialApi
@Preview(widthDp = 400, heightDp = 700)
@Composable
fun CharacterDetailScreenPreview() {
    val character = Character(
        1,
        "iron",
        "lorem",
        "",
        listOf(Reference("Comic 1"), Reference("Comic 2")),
        listOf(Reference("Comic 1"), Reference("Comic 2")),
        listOf(Reference("Comic 1"), Reference("Comic 2")),
        listOf(Reference("Comic 1"), Reference("Comic 2"))
    )
    MarvelApp {
        CharacterDetailScreen(character)
    }
}