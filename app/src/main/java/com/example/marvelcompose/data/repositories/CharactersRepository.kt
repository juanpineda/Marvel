package com.example.marvelcompose.data.repositories

import com.example.marvelcompose.data.entities.Character
import com.example.marvelcompose.data.network.ApiClient

class CharactersRepository : Repository<Character>() {

    suspend fun get(): List<Character> = super.get {
        ApiClient
            .charactersService
            .getCharacters(0, 100)
            .data
            .results
            .map { it.asCharacter() }
    }

    suspend fun find(id: Int): Character = super.find(
        findActionLocal = { it.id == id },
        findActionRemote = {
            ApiClient
                .charactersService
                .findCharacter(id)
                .data
                .results
                .first()
                .asCharacter()
        }
    )
}