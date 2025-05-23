package com.example.movies_app.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.movies_app.databinding.ActivityMovieDetailsBinding
import com.example.movies_app.network.RetrofitClient
import com.example.movies_app.repo.MovieRepository
import com.example.movies_app.viewmodel.MovieDetailsViewModel
import com.example.movies_app.viewmodel.MovieDetailsViewModelFactory

class MovieDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieDetailsBinding

    private val viewModel: MovieDetailsViewModel by viewModels {
        MovieDetailsViewModelFactory(MovieRepository(RetrofitClient.api))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Enable back button in the action bar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Movie Details"

        val imdbID = intent.getStringExtra("imdbID")
        if (imdbID.isNullOrEmpty()) {
            Toast.makeText(this, "No movie found", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        viewModel.fetchMovieDetails(imdbID)

        viewModel.movieDetails.observe(this) { details ->
            if (details != null) {
                binding.tvTitle.text = details.Title
                binding.tvYear.text = "Year: ${details.Year}"
                binding.tvRating.text = "IMDB Rating: ${details.imdbRating}"
                binding.tvDescription.text = details.Plot
                Glide.with(this)
                    .load(details.Poster)
                    .into(binding.imgPoster)
            } else {
                Toast.makeText(this, "Failed to load movie details", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}