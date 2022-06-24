package com.example.marvelcompose.data.network.entities

data class Stories(
    val available: Int,
    val collectionURI: String,
    val items: List<story>,
    val returned: Int
)