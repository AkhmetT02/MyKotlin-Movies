package com.example.mykotlinmovies.screens.favouriteScreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mykotlinmovies.R
import com.example.mykotlinmovies.adapter.MovieAdapter
import com.example.mykotlinmovies.database.DatabaseViewModel
import com.example.mykotlinmovies.pojo.Result
import com.example.mykotlinmovies.screens.detailScreen.DetailActivity
import com.example.mykotlinmovies.screens.mainScreen.MainActivity
import kotlinx.android.synthetic.main.activity_favourite_movies.*

class FavouriteMoviesActivity : AppCompatActivity() {

    private val viewModel: DatabaseViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favourite_movies)

        val moviesAdapter = MovieAdapter(this)
        recycler_favourite_movies.adapter = moviesAdapter
        recycler_favourite_movies.layoutManager = GridLayoutManager(this, 2)

        viewModel.movies.observe(this, Observer {
            moviesAdapter.movies = it as ArrayList<Result>
        })

        moviesAdapter.setOnPosterClickListener(object: MovieAdapter.OnPosterClickListener{
            override fun onPosterClick(position: Int) {
                val intent = Intent(baseContext, DetailActivity::class.java)
                intent.putExtra("movieId", moviesAdapter.movies[position].id)
                startActivity(intent)
            }
        })
    }

    fun onMenuClick(view: View){
        drawer_layout.openDrawer(GravityCompat.START)
    }
    fun onHomeClick(view: View){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
    fun onFavouriteMoviesClick(view: View){
        recreate()
        drawer_layout.closeDrawer(GravityCompat.START)
    }
}