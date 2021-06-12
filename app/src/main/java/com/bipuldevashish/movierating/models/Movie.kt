package com.bipuldevashish.movierating.models

data class Movie(
    val id : Int,
    val adult : Boolean,
    val release_date : String,
    val vote_average : Number,
    val poster_path: String,
    val overview: String,
    val title : String
)