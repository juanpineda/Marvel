package com.example.marvelcompose.data.repositories

import com.example.marvelcompose.data.entities.Character
import com.example.marvelcompose.data.entities.Reference
import com.example.marvelcompose.data.network.ApiClient
import com.example.marvelcompose.data.network.entities.asString
import com.example.marvelcompose.data.network.entities.Character as NetworkCharacter

class CharactersRepository {

    suspend fun getCharacters(): List<Character> {
        val result = ApiClient.charactersService.getCharacters(0, 100)

        return result.data.results.map {
            it.asCharacter()
        }
    }

    suspend fun findCharacters(characterId: Int): Character {
        val result = ApiClient.charactersService.findCharacter(characterId)
        return result.data.results.first().asCharacter()
    }
}

fun NetworkCharacter.asCharacter(): Character {
    val comics = comics.items.map { Reference(it.name) }
    val series = series.items.map { Reference(it.name) }
    val events = events.items.map { Reference(it.name) }
    val stories = stories.items.map { Reference(it.name) }
    return Character(
        id,
        name,
        description,
        thumbnail.asString(),
        comics,
        series,
        events,
        stories
    )
}