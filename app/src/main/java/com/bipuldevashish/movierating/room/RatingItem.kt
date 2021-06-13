package com.bipuldevashish.movierating.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
            tableName = "user_rating"
)

data class RatingItem (
        @PrimaryKey(autoGenerate = false)
        @ColumnInfo val id: Int,
        @ColumnInfo var my_rating : Double
        )
