package com.example.movies_app.model

data class Movie(
    val imdbID: String,
    val Title: String,
    val Year: String,
    val Poster: String,
    val Production: String?,
    val imdbRating: String?,
    val Plot: String?
)
data class MovieSearchResponse(
    val Search: List<Movie>?,
    val totalResults: String?,
    val Response: String?
)