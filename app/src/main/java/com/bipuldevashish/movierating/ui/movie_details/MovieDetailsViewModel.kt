package com.bipuldevashish.movierating.ui.movie_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bipuldevashish.movierating.room.RatingDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(ratingDao: RatingDao): ViewModel() {

    private var _rate: MutableLiveData<Double?> = MutableLiveData<Double?>()
    val rate: LiveData<Double?>
        get() = _rate
    val dao = ratingDao


    suspend fun getData(movieId: Int){
        _rate.postValue(dao.findRating(movieId))
    }

}