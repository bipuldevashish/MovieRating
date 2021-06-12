package com.bipuldevashish.movierating.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.bipuldevashish.movierating.api.ApiService
import com.bipuldevashish.movierating.paging.MovieListPagingSource
import javax.inject.Inject
import javax.inject.Singleton

private const val TAG = "Repository"
@Singleton
class Repository @Inject constructor(private val apiService: ApiService) {

       fun getMovieList()  =
           Pager(
               config = PagingConfig(
                   pageSize = 10,
                   maxSize = 30,
                   enablePlaceholders = false
               ),
               pagingSourceFactory = { MovieListPagingSource(apiService) }
           ).liveData
}