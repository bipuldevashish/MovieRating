package com.bipuldevashish.movierating.di

import android.content.Context
import androidx.room.Room
import com.bipuldevashish.movierating.api.ApiService
import com.bipuldevashish.movierating.room.RatingDao
import com.bipuldevashish.movierating.room.RatingDatabase
import com.bipuldevashish.movierating.util.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesRetrofit() : Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideApiService( retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideRatingDao( ratingDatabase: RatingDatabase) : RatingDao {
        return ratingDatabase.ratingDao()
    }

    @Provides
    @Singleton
    fun provideRatingDatabase(@ApplicationContext appContext: Context) : RatingDatabase {
        return Room.databaseBuilder(
            appContext,
            RatingDatabase::class.java,
            "rating_database"
        ).build()
    }


}