package com.mtuci.akino.api

import com.mtuci.akino.details.MovieDetails
import com.mtuci.akino.main.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie")
    suspend fun getMovieList(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): MoviesResponse

    @GET("movie/{id}")
    suspend fun getMovieDetails(
        @Path("id") id: Int
    ): MovieDetails
}