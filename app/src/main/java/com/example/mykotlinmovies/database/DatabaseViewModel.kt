package com.example.mykotlinmovies.database

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.mykotlinmovies.pojo.Result
import java.util.concurrent.Callable
import java.util.concurrent.Future

class DatabaseViewModel(application: Application) : AndroidViewModel(application) {

    private val database = MovieDatabase.getDatabase(application)
    val movies = database.getMovieDao().getAllMovies()

    fun insertMovie(result: Result){
        MovieDatabase.databaseWriteExecutor.execute {
            database.getMovieDao().insertMovie(result)
            Log.i("InsertTag", "HEYYYYY")
        }
    }

    fun getMovieById(id: Int) : Result{
        return MovieDatabase.databaseWriteExecutor.submit(GetMovieByIdCallable(id)).get()
    }

    fun deleteMovie(result: Result){
        MovieDatabase.databaseWriteExecutor.execute {
            database.getMovieDao().deleteMovie(result)
        }
    }

    inner class GetMovieByIdCallable(private val id: Int) : Callable<Result>{
        override fun call(): Result {
            return database.getMovieDao().getMovieById(id)
        }
    }
}