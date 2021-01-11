package com.example.mykotlinmovies.screens.detailScreen

import com.example.mykotlinmovies.pojo.Result
import com.example.mykotlinmovies.pojo.Review
import com.example.mykotlinmovies.pojo.Video

interface DetailListView {
    fun showSimilarMovie(similarMovies: List<Result>)
    fun showVideo(videos: List<Video>)
    fun showReview(reviews: List<Review>)
}