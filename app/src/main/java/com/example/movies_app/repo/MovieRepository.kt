package com.example.movies_app.repo

import com.example.movies_app.model.Movie
import com.example.movies_app.network.OMDBApi

class MovieRepository(private val api: OMDBApi) {
    private val apiKey = "23fc907a"


    suspend fun searchMovies(query: String): List<Movie> {
        val response = api.search(apiKey, query)
        val searchList = if (response.isSuccessful) response.body()?.Search ?: emptyList() else emptyList()
        return searchList.mapNotNull { shortMovie ->
            val detailsResp = api.getMovieDetails(apiKey, shortMovie.imdbID)
            if (detailsResp.isSuccessful) detailsResp.body() else null
        }
    }

    suspend fun getMovieDetails(imdbID: String): Movie? {
        val response = api.getMovieDetails(apiKey, imdbID)
        return if (response.isSuccessful) response.body() else null
    }
}