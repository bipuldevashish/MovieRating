package com.bipuldevashish.movierating.ui.movie_details

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.bipuldevashish.movierating.R
import com.bipuldevashish.movierating.databinding.FragmentMovieDetailsBinding
import com.bipuldevashish.movierating.util.Constants
import com.bipuldevashish.movierating.util.Utils.loadImage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieDetailsFragment : Fragment(R.layout.fragment_movie_details) {

    private val TAG = "MovieDetailsFragment"

    private val args by navArgs<MovieDetailsFragmentArgs>()
    private var viewModel: MovieDetailsViewModel? = null
    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentMovieDetailsBinding.bind(view)
        viewModel = ViewModelProvider(requireActivity()).get(MovieDetailsViewModel::class.java)

        binding.apply {
            val movie = args.data
            detailPoster.loadImage(Constants.BASE_IMAGE_URL_500+movie.backdrop_path)
            detailedThumbnail.loadImage(Constants.BASE_IMAGE_URL+movie.poster_path)
            movieTitle.text = movie.title
            voteCount.text = getString(R.string.movie_rating)+movie.vote_average
            movieYear.text = getString(R.string.movie_date)+movie.release_date
            if (movie.adult){
                detailsAdult.text = getString(R.string.age_yes)
            }else{
                detailsAdult.text = getString(R.string.age_no)
            }
            overview.text = movie.overview

            btnRateNow.setOnClickListener{
                val action = MovieDetailsFragmentDirections.actionMovieDetailsToRateMovie(movie.id)
                view.findNavController().navigate(action)
            }
        }
        checkDb(binding)
    }

    private fun checkDb(binding: FragmentMovieDetailsBinding) {

        lifecycleScope.launch {
            viewModel?.getData(args.data.id)
        }

        viewModel?.rate?.observe(viewLifecycleOwner, {
            if (it != null){
                binding.layoutRate.visibility = View.GONE
                binding.tvRating.text = it.toString()
            }else{
                binding.layoutRate.visibility = View.VISIBLE
                binding.tvRating.visibility = View.GONE

            }
        })
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: called")
    }

    override fun onStart() {
        super.onStart()
        checkDb(binding)
        Log.d(TAG, "onStart: called")
    }

    override fun onResume() {
        super.onResume()
        checkDb(binding)
        Log.d(TAG, "onResume: called")
    }


}