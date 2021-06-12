package com.bipuldevashish.movierating.api

import com.bipuldevashish.movierating.models.MovieList
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {
    @GET("3/movie/popular")
    suspend fun getMovieList(
        @Query("page") page: Int,
        @Query("api_key") api_key : String
    ): MovieList
}