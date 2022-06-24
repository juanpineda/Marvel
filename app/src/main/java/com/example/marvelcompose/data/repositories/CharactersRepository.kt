package com.example.marvelcompose.data.repositories

import com.example.marvelcompose.data.entities.Character
import com.example.marvelcompose.data.network.ApiClient
import com.example.marvelcompose.data.network.entities.asString

class CharactersRepository {

    suspend fun getCharacters(): List<Character> {
        val result = ApiClient.charactersService.getCharacters(0, 100)

        return result.data.results.map {
            Character(it.id, it.name, it.description, it.thumbnail.asString())
        }
    }
}