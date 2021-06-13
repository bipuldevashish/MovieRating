package com.bipuldevashish.movierating.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [RatingItem::class], version = 1, exportSchema = false)

abstract class RatingDatabase : RoomDatabase(){

        //to create or access an instance of ImageDao
        abstract fun ratingDao() : RatingDao

        /*
            ImageData should have only one instance to avoid work load
         */

        companion object{
        @Volatile
        private var INSTANCE : RatingDatabase? = null

            fun getDatabase(context: Context) : RatingDatabase {
                val tempInstance = INSTANCE

            /*
            check for instance if already exists,else create one
             */
                if (tempInstance != null){
                    return tempInstance
                }

                synchronized(this){
                    val instance = Room.databaseBuilder(    //  Database Builder : build's the database
                        context.applicationContext,         //  Provide the Context
                        RatingDatabase::class.java,          //  Table format for Database
                        "rating_database"             //  Name for the Database
                    ).build()                               //  Build fun. to execute the builder.
                    INSTANCE = instance                     //  Assigning local var. instance to INSTANCE
                    return instance                         //  Returning the instance
                }
            }
        }
}