package com.example.movies_app.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movies_app.R
import com.example.movies_app.model.Movie

class MovieAdapter(
    private var movies: List<Movie>,
    private val onItemClick: (Movie) -> Unit
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val tvStudio: TextView = itemView.findViewById(R.id.tvStudio)
        val tvRating: TextView = itemView.findViewById(R.id.tvRating)
        val tvYear: TextView = itemView.findViewById(R.id.tvYear)

        init {
            itemView.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    onItemClick(movies[adapterPosition])
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.tvTitle.text = movie.Title
        holder.tvStudio.text = "Studio: ${movie.Production ?: "N/A"}"
        holder.tvRating.text = "Rating: ${movie.imdbRating ?: "N/A"}"
        holder.tvYear.text = "Year: ${movie.Year}"
    }

    override fun getItemCount() = movies.size

    fun setMovies(list: List<Movie>) {
        movies = list
        notifyDataSetChanged()
    }
}