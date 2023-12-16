package com.capstonebangkit.voishy.data.network

import com.capstonebangkit.voishy.data.response.ArticlesResponse
import com.capstonebangkit.voishy.data.response.MovieDetailResponse
import com.capstonebangkit.voishy.data.response.TopRatedMovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/top_rated")
    suspend fun getTopRatedMovie(
        @Query("api_key") apiKey: String
    ): TopRatedMovieResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String
    ): MovieDetailResponse
}

interface ApiServiceArticle{
    @GET("getAll")
    suspend fun getArticle(): ArticlesResponse
}
