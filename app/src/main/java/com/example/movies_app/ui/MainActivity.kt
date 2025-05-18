package com.example.movies_app.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movies_app.databinding.ActivityMainBinding
import com.example.movies_app.model.Movie
import com.example.movies_app.network.RetrofitClient
import com.example.movies_app.repo.MovieRepository
import com.example.movies_app.viewmodel.HomeViewModel
import com.example.movies_app.viewmodel.HomeViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MovieAdapter

    private val viewModel: HomeViewModel by viewModels {
        HomeViewModelFactory(MovieRepository(RetrofitClient.api))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = MovieAdapter(emptyList()) { movie ->
            val intent = Intent(this, MovieDetailsActivity::class.java)
            intent.putExtra("imdbID", movie.imdbID)
            startActivity(intent)
        }
        binding.rvMovies.layoutManager = LinearLayoutManager(this)
        binding.rvMovies.adapter = adapter

        binding.btnSearch.setOnClickListener {
            val q = binding.etSearch.text.toString().trim()
            if (q.isEmpty()) {
                Toast.makeText(this, "Please enter a search term", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.searchMovies(q)
            }
        }

        viewModel.movies.observe(this) { list ->
            adapter.setMovies(list)
        }
    }
}