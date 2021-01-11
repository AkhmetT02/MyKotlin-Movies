package com.example.mykotlinmovies.client

import com.example.mykotlinmovies.pojo.Movie
import com.example.mykotlinmovies.pojo.Result
import com.example.mykotlinmovies.pojo.ReviewResult
import com.example.mykotlinmovies.pojo.VideoResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface RetrofitServices {
    @GET("3/discover/movie/")
    fun getMoviesList(@Query("api_key") apiKey: String, @Query("page") page: Int) : Call<Movie>

    @GET("3/movie/{movieId}/videos")
    fun getVideosList(@Path("movieId") movieId: Int, @Query("api_key") apiKey: String) : Call<VideoResult>

    @GET("3/movie/{movieId}/similar")
    fun getSimilarMoviesList(@Path("movieId") movieId: Int, @Query("api_key") apiKey: String) : Call<Movie>

    @GET("3/movie/{movieId}/reviews")
    fun getReviewsList(@Path("movieId") movieId: Int, @Query("api_key") apiKey: String) : Call<ReviewResult>
}