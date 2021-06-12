package com.bipuldevashish.movierating.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.bipuldevashish.movierating.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

private const val TAG = "MovieListViewModel"

@HiltViewModel
class MovieListViewModel @Inject constructor(repository: Repository) : ViewModel() {
    init {
        Log.d("MovieListViewModel", "GameViewModel created!")
    }
    val movies = repository.getMovieList().cachedIn(viewModelScope)

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "onCleared: MovieListViewModel destroyed")
    }
}