package com.example.marvelcompose.data.repositories

import com.example.marvelcompose.data.entities.Event
import com.example.marvelcompose.data.network.ApiClient
import com.example.marvelcompose.data.network.entities.Result

object EventsRepository : Repository<Event>() {

    suspend fun get(): Result<List<Event>> = super.get {
        ApiClient
            .eventsService
            .getEvents(0, 100)
            .data
            .results
            .map { it.asEvent() }
    }

    suspend fun find(id: Int): Result<Event> = super.find(
        id,
        findActionRemote = {
            ApiClient
                .eventsService
                .findEvent(id)
                .data
                .results
                .first()
                .asEvent()
        }
    )
}