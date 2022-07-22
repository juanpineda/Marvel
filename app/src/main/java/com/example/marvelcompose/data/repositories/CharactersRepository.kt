package com.example.marvelcompose.data.repositories

import com.example.marvelcompose.data.entities.Character
import com.example.marvelcompose.data.network.ApiClient
import com.example.marvelcompose.data.network.entities.Result

object CharactersRepository : Repository<Character>() {

    suspend fun get(): Result<List<Character>> = super.get {
        ApiClient
            .charactersService
            .getCharacters(0, 100)
            .data
            .results
            .map { it.asCharacter() }
    }

    suspend fun find(id: Int): Result<Character> = super.find(
        id,
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