package com.example.mykotlinmovies.pojo

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "movies")
data class Result(
        @PrimaryKey(autoGenerate = true)
        val uniqueId: Int,
        val adult: Boolean,
        val backdrop_path: String?,
//        val genre_ids: List<Int>,
        val id: Int,
        val original_language: String?,
        val original_title: String?,
        val overview: String?,
        val popularity: Double,
        val poster_path: String?,
        val release_date: String?,
        val title: String?,
        val video: Boolean,
        val vote_average: Double,
        val vote_count: Int
)