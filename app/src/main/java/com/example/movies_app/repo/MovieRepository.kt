package com.example.movies_app.repo

import com.example.movies_app.model.Movie
import com.example.movies_app.model.MovieDetails
import com.example.movies_app.network.OMDBApi

class MovieRepository(private val api: OMDBApi) {
    private val apiKey = "23fc907a"
    suspend fun searchMovies(query: String): List<Movie> {
        val response = api.search(apiKey, query)
        return if (response.isSuccessful) response.body()?.Search ?: emptyList() else emptyList()
    }

    suspend fun getMovieDetails(imdbID: String): MovieDetails? {
        val response = api.getMovieDetails(apiKey, imdbID)
        return if (response.isSuccessful) response.body() else null
    }
}