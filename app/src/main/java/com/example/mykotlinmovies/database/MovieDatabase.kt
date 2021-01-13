package com.example.mykotlinmovies.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mykotlinmovies.pojo.Result
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.locks.ReentrantLock

@Database(entities = [Result::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {


    companion object {

        private var database: MovieDatabase? = null
        private const val DB_NAME = "movies.db"
        private val LOCK = ReentrantLock()

        private const val THREAD_COUNT = 4
        val databaseWriteExecutor = Executors.newFixedThreadPool(THREAD_COUNT)

        fun getDatabase(context: Context): MovieDatabase {
//            synchronized(LOCK) {
                if (database == null) {
                    database = Room.databaseBuilder(context, MovieDatabase::class.java, DB_NAME)
                        .fallbackToDestructiveMigration()
                        .build()
                }
//            }
            return database!!
        }
    }

    abstract fun getMovieDao() : MovieDao
}