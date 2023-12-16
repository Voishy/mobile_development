package com.capstonebangkit.voishy.data

import com.capstonebangkit.voishy.BuildConfig
import com.capstonebangkit.voishy.data.network.ApiService
import com.capstonebangkit.voishy.data.response.MovieDetailResponse
import com.capstonebangkit.voishy.data.response.ResultsItem

class Repository private constructor(
    private val apiService: ApiService
){
    companion object {
        @Volatile
        private var instance: Repository? = null

        fun getInstance(
            apiService: ApiService
        ) : Repository =
            instance ?: synchronized(this) {
                instance ?: Repository(apiService)
            }.also { instance = it }
    }

    suspend fun getNews() : List<ResultsItem> {
        val response = apiService.getTopRatedMovie(BuildConfig.API_KEY_MOVIE)
        return response.results
    }

    suspend fun getMovieDetailbyID(id: Int): MovieDetailResponse {
        return apiService.getMovieDetail(id, BuildConfig.API_KEY_MOVIE)
    }
}