package com.example.mykotlinmovies.screens.mainScreen

import com.example.mykotlinmovies.pojo.Result

interface MainListView {
    fun showData(movies: List<Result>?)
    fun showError()
    fun showSearchedData(movies: List<Result>?)
}