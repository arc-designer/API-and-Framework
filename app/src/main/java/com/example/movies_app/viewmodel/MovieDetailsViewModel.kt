package com.example.movies_app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies_app.model.Movie
import com.example.movies_app.repo.MovieRepository
import kotlinx.coroutines.launch

class MovieDetailsViewModel(private val repo: MovieRepository) : ViewModel() {
    private val _movieDetails = MutableLiveData<Movie?>()
    val movieDetails: LiveData<Movie?> = _movieDetails

    fun fetchMovieDetails(imdbID: String) {
        viewModelScope.launch {
            _movieDetails.value = repo.getMovieDetails(imdbID)
        }
    }
}