package com.bipuldevashish.movierating.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RatingDao {

            // on inserting new data if conflict occurs it resolves the issue
            @Insert(onConflict = OnConflictStrategy.REPLACE)
            suspend fun addRating(ratingItem : RatingItem)

            @Query("SELECT my_rating FROM user_rating WHERE id = :movie_id")
            suspend fun findRating(movie_id : Int) : Double
}