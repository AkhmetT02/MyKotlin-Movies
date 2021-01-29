package com.example.mykotlinmovies.database

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.mykotlinmovies.pojo.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.Callable
import java.util.concurrent.Future
import kotlin.properties.Delegates

class DatabaseViewModel(application: Application) : AndroidViewModel(application) {

    private val database = MovieDatabase.getDatabase(application)
    val movies = database.getMovieDao().getAllMovies()

    fun insertMovie(result: Result) = CoroutineScope(Dispatchers.Default).launch{
        database.getMovieDao().insertMovie(result)
    }

    suspend fun getMovieById(id: Int) : Result = withContext(Dispatchers.IO){
        database.getMovieDao().getMovieById(id)
    }


    fun deleteMovie(id: Int) = CoroutineScope(Dispatchers.IO).launch{
        database.getMovieDao().deleteMovie(id)
    }
}