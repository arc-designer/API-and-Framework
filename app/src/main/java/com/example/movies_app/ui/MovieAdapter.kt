package com.example.movies_app.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movies_app.R
import com.example.movies_app.model.Movie

class MovieAdapter(private var movies: List<Movie>, val onItemClick: (Movie) -> Unit) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.tvTitle)
        val year: TextView = itemView.findViewById(R.id.tvYear)
        val type: TextView = itemView.findViewById(R.id.tvType)
        init {
            itemView.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) onItemClick(movies[adapterPosition])
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
        holder.title.text = movie.Title
        holder.year.text = movie.Year
        holder.type.text = movie.Type
    }

    override fun getItemCount() = movies.size

    fun setMovies(list: List<Movie>) {
        movies = list
        notifyDataSetChanged()
    }
}