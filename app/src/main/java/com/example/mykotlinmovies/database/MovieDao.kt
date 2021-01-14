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

    @Query("DELETE FROM movies WHERE id = :id")
    fun deleteMovie(id: Int)

    @Insert
    fun insertMovie(result: Result)
}