package com.bipuldevashish.movierating.models

data class  MovieList(

    val page: Int,
    val per_page: Int,
    val total: Int,
    val total_pages: Int,
    val data: List<Movie> = emptyList(),
    val support : Support

)






