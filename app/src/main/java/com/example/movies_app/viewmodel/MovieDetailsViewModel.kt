package com.example.movies_app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies_app.model.MovieDetails
import com.example.movies_app.network.OMDBApi
import com.example.movies_app.repo.MovieRepository

import kotlinx.coroutines.launch

class MovieDetailsViewModel(private val repo: MovieRepository) : ViewModel() {
    private val _movieDetails = MutableLiveData<MovieDetails?>()
    val movieDetails: LiveData<MovieDetails?> = _movieDetails

    fun fetchMovieDetails(imdbID: String) {
        viewModelScope.launch {
            _movieDetails.value = repo.getMovieDetails(imdbID)
        }
    }
}