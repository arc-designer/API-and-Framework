package com.example.movies_app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies_app.model.Movie
import com.example.movies_app.repo.MovieRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val repo: MovieRepository) : ViewModel() {
    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> = _movies

    fun searchMovies(query: String) {
        viewModelScope.launch {
            _movies.value = repo.searchMovies(query)
        }
    }
}