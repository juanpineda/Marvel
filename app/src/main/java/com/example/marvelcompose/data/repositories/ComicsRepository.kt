package com.example.marvelcompose.data.repositories

import com.example.marvelcompose.data.entities.Comic
import com.example.marvelcompose.data.network.ApiClient
import com.example.marvelcompose.data.network.entities.Result
import com.example.marvelcompose.data.network.entities.tryCall

object ComicsRepository {

    suspend fun get(format: Comic.Format): Result<List<Comic>> = tryCall {
        ApiClient
            .comicsService
            .getComics(0, 20, format.toStringFormat())
            .data
            .results
            .map { it.asComic() }
    }

    suspend fun find(id: Int): Result<Comic> = tryCall {
        ApiClient
            .comicsService
            .findComic(id)
            .data
            .results
            .first()
            .asComic()
    }
}