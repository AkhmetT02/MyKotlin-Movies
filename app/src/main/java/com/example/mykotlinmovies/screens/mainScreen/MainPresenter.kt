package com.example.mykotlinmovies.screens.mainScreen

import com.example.mykotlinmovies.client.Common
import com.example.mykotlinmovies.client.RetrofitServices
import com.example.mykotlinmovies.pojo.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainPresenter(private val view: MainListView) {

    private lateinit var mService: RetrofitServices
    val API_KEY: String = "26c9c63599343ed01a5489135d890a5e"

    fun getData(page: Int){
        mService = Common.getRetrofitService
        mService.getMoviesList(API_KEY, page).enqueue(object: Callback<Movie> {
            override fun onFailure(call: Call<Movie>, t: Throwable) {
                view.showError()
            }

            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                view.showData(response.body()?.results)
//                Log.i("RESPONSE_MOVIES_LENGTH", movies.size.toString())
            }
        })
    }
}