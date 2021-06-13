package com.bipuldevashish.movierating.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bipuldevashish.movierating.api.ApiService
import com.bipuldevashish.movierating.models.Movie
import com.bipuldevashish.movierating.util.Constants
import com.bipuldevashish.movierating.util.Constants.Companion.USERS_STARTING_PAGE_INDEX
import retrofit2.HttpException
import java.io.IOException

class MovieListPagingSource(
    private val api: ApiService
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val position = params.key ?: USERS_STARTING_PAGE_INDEX

        return try {
            val response = api.getMovieList(position, Constants.API_KEY)
            val users = response.results
            LoadResult.Page(
                data = users,
                prevKey = if (position == USERS_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (users.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        TODO("Not yet implemented")
    }

}