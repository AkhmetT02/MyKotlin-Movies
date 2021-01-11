package com.example.mykotlinmovies.pojo



data class Review(
    val author: String,
    val author_details: ReviewAuthorDetail,
    val content: String,
    val created_at: String,
    val id: String,
    val updated_at: String,
    val url: String
)
