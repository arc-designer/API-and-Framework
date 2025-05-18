package com.example.movies_app.model

data class SearchResponse(
    val Search: List<Movie>?,
    val totalResults: String?,
    val Response: String
)