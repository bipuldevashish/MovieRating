package com.bipuldevashish.movierating.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bipuldevashish.movierating.api.ApiService
import com.bipuldevashish.movierating.models.Movie
import com.bipuldevashish.movierating.util.Constants
import retrofit2.HttpException
import java.io.IOException

private const val USERS_STARTING_PAGE_INDEX = 1
private const val TAG = "MovieListPagingSource"

class MovieListPagingSource(
    private val api: ApiService
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val position = params.key ?: USERS_STARTING_PAGE_INDEX
        Log.d(TAG, "load: called , position = $position")

        return try {
            Log.d(TAG, "load: entered try block")
            val response = api.getMovieList(position, Constants.API_KEY)
            Log.d(TAG, "load: $response")
            val users = response.results
            Log.d(TAG, "load: before users printing statement")
            Log.d(TAG, "load: $users")
            LoadResult.Page(
                data = users,
                prevKey = if (position == USERS_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (users.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            Log.d(TAG, "load: caught exception $exception")
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            Log.d(TAG, "load: caught exception $exception")
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        TODO("Not yet implemented")
    }

}