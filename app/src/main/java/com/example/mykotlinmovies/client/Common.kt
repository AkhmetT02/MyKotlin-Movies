package com.example.mykotlinmovies.client

object Common {

    private const val BASE_URL: String = "https://api.themoviedb.org/"

    val getRetrofitService: RetrofitServices
        get() = RetrofitClient.getInstance(BASE_URL).create(RetrofitServices::class.java)

}