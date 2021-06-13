package com.bipuldevashish.movierating.ui.rate_movie

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bipuldevashish.movierating.R
import com.bipuldevashish.movierating.databinding.FragmentRateMovieBinding
import com.bipuldevashish.movierating.room.RatingItem


class RateMovieFragment : Fragment(R.layout.fragment_rate_movie) {

    private var viewModel: RateMovieViewModel? = null
    private val args by navArgs<RateMovieFragmentArgs>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(RateMovieViewModel::class.java)


        val binding = FragmentRateMovieBinding.bind(view)
        binding.apply {

            val id = args.id

            ratingBar.setOnRatingBarChangeListener { _, p1, _ -> ratingText.text = (p1*2).toString() }
            btnSubmit.setOnClickListener {
                val irate = ratingBar.rating
                val convertIrate: Double = String.format("%.1f", irate*2).toDouble()

                val myRating = RatingItem(id, convertIrate)
                saveToRoom(myRating)
            }
        }
    }

    private fun saveToRoom(ratingItem: RatingItem) {
        viewModel?.addRating(ratingItem)
        Toast.makeText(requireContext(),
            "You rated ${ratingItem.my_rating} out of 10", Toast.LENGTH_SHORT)
            .show()
    }


}