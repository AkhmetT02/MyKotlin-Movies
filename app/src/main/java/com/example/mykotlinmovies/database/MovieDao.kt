package com.example.mykotlinmovies.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.mykotlinmovies.pojo.Result
import java.util.concurrent.Callable

@Dao
interface MovieDao {
    @Query("SELECT * FROM movies")
    fun getAllMovies() : LiveData<List<Result>>

    @Query("SELECT * FROM movies WHERE id = :id")
    fun getMovieById(id: Int) : Result

    @Delete
    fun deleteMovie(result: Result)

    @Insert
    fun insertMovie(result: Result)
}