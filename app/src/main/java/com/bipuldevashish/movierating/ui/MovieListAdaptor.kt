package com.bipuldevashish.movierating.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bipuldevashish.movierating.R
import com.bipuldevashish.movierating.models.Movie
import com.bipuldevashish.movierating.util.Constants
import com.bipuldevashish.movierating.util.Utils.loadImage

class MovieListAdaptor :
    PagingDataAdapter<Movie, MovieListAdaptor.MovieListViewHolder>(USER_COMPARATOR) {


    companion object {
        private val USER_COMPARATOR = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem == newItem


            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem == newItem

        }
    }

    inner class MovieListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val imageView = itemView.findViewById<ImageView>(R.id.movie_poster)
        private val movieName = itemView.findViewById<TextView>(R.id.tv_movie_name)
        private val movieRate = itemView.findViewById<TextView>(R.id.tv_movie_rating)
        private val movieDate = itemView.findViewById<TextView>(R.id.tv_release_date)
        private val movieAdult = itemView.findViewById<TextView>(R.id.tv_adult)

        fun bind(item: Movie) {
            movieName.text = item.title
            movieRate.text = item.vote_average.toString()
            movieDate.text = item.release_date
            movieAdult.text = item.adult.toString()
            imageView.loadImage(Constants.BASE_IMAGE_URL+item.poster_path)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        return MovieListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie_details, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        getItem(position)?.let { (holder as? MovieListViewHolder)?.bind(item = it) }
    }
}