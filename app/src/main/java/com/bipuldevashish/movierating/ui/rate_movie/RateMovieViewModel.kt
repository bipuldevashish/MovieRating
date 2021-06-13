package com.bipuldevashish.movierating.ui.rate_movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bipuldevashish.movierating.repository.Repository
import com.bipuldevashish.movierating.room.RatingItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RateMovieViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    fun addRating(ratingItem: RatingItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addRating(ratingItem)
        }
    }
}