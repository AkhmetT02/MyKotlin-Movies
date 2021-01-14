package com.example.mykotlinmovies.screens.detailScreen

import com.example.mykotlinmovies.client.Common
import com.example.mykotlinmovies.client.RetrofitServices
import com.example.mykotlinmovies.database.DatabaseViewModel
import com.example.mykotlinmovies.pojo.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.activity.viewModels

class DetailPresenter(private val view: DetailListView) {

    private lateinit var mServices: RetrofitServices
    val API_KEY: String = "26c9c63599343ed01a5489135d890a5e"


    fun getData(movieId: Int){
        mServices = Common.getRetrofitService
        mServices.getVideosList(movieId, API_KEY).enqueue(object: Callback<VideoResult> {
            override fun onResponse(call: Call<VideoResult>, response: Response<VideoResult>) {
                view.showVideo(response.body()?.results as ArrayList<Video>)
            }

            override fun onFailure(call: Call<VideoResult>, t: Throwable) {

            }
        })
        mServices.getSimilarMoviesList(movieId, API_KEY).enqueue(object: Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                view.showSimilarMovie(response.body()?.results as ArrayList<Result>)
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
        mServices.getReviewsList(movieId, API_KEY).enqueue(object: Callback<ReviewResult> {
            override fun onResponse(call: Call<ReviewResult>, response: Response<ReviewResult>) {
                view.showReview(response.body()?.results as ArrayList<Review>)
            }

            override fun onFailure(call: Call<ReviewResult>, t: Throwable) {

            }
        })

        mServices.getMovieById(movieId, API_KEY).enqueue(object: Callback<Result>{
            override fun onResponse(call: Call<Result>, response: Response<Result>) {
                response.body()?.let { view.showMovie(it) }
            }

            override fun onFailure(call: Call<Result>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })


    }
}