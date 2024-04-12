package com.mtuci.akino.api

import com.mtuci.akino.details.data.MovieDetails
import com.mtuci.akino.details.data.PostersResponse
import com.mtuci.akino.details.data.ReviewResponse
import com.mtuci.akino.main.data.MoviesResponse
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

    @GET("image")
    suspend fun getMoviePosters(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("movieId") movieId: Int
    ): PostersResponse

    @GET("review")
    suspend fun getMovieReviews(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("movieId") movieId: Int
    ): ReviewResponse
}