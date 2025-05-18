package com.example.movies_app.network

import com.example.movies_app.model.MovieDetails
import com.example.movies_app.model.MovieSearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface OMDBApi {
    @GET("/")
    suspend fun search(
        @Query("apikey") apiKey: String,
        @Query("s") query: String
    ): Response<MovieSearchResponse>

    @GET("/")
    suspend fun getMovieDetails(
        @Query("apikey") apiKey: String,
        @Query("i") imdbID: String,
        @Query("plot") plot: String = "full"
    ): Response<MovieDetails>
}