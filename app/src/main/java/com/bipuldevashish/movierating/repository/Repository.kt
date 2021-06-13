package com.bipuldevashish.movierating.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.bipuldevashish.movierating.api.ApiService
import com.bipuldevashish.movierating.paging.MovieListPagingSource
import com.bipuldevashish.movierating.room.RatingDao
import com.bipuldevashish.movierating.room.RatingItem
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val apiService: ApiService,
    private val ratingDao: RatingDao) {

       fun getMovieList()  =
           Pager(
               config = PagingConfig(
                   pageSize = 10,
                   maxSize = 30,
                   enablePlaceholders = false
               ),
               pagingSourceFactory = { MovieListPagingSource(apiService) }
           ).liveData

        suspend fun addRating(ratingItem: RatingItem){
            ratingDao.addRating(ratingItem)
        }

        suspend fun findRating(id: Int) : Double = ratingDao.findRating(id)

}