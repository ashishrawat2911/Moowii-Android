package com.ashish.moowii.data.source.remote

import android.net.http.HttpException
import androidx.annotation.RequiresApi
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ashish.moowii.data.store.MovieDataStore
import com.ashish.moowii.domain.model.MovieDomainModel
import java.io.IOException

class MoviePagingSource(
    private val movieDataStore: MovieDataStore,
) : PagingSource<Int, MovieDomainModel>() {

    @RequiresApi(34)
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieDomainModel> {
        return try {
            val currentPage = params.key ?: 1
            val movies = movieDataStore.getMovies(
                page = currentPage
            )
            LoadResult.Page(
                data = movies.results,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (movies.results.isEmpty()) null else movies.page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieDomainModel>): Int? {
        return state.anchorPosition
    }

}