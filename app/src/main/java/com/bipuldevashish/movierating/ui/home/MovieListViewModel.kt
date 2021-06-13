package com.bipuldevashish.movierating.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.bipuldevashish.movierating.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MovieListViewModel @Inject constructor(repository: Repository) : ViewModel() {

    val movies = repository.getMovieList().cachedIn(viewModelScope)

}