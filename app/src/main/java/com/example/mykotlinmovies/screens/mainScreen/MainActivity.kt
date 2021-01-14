package com.example.mykotlinmovies.screens.mainScreen

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mykotlinmovies.R
import com.example.mykotlinmovies.adapter.MovieAdapter
import com.example.mykotlinmovies.pojo.Result
import com.example.mykotlinmovies.screens.detailScreen.DetailActivity
import com.example.mykotlinmovies.screens.favouriteScreen.FavouriteMoviesActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainListView {

    private lateinit var adapter: MovieAdapter
    private lateinit var presenter: MainPresenter

    private var PAGE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MainPresenter(this)
        adapter = MovieAdapter(this)

        recycler_movies.layoutManager = GridLayoutManager(this, 2)
        recycler_movies.adapter = adapter

        presenter.getData(PAGE)

        adapter.setOnReachEndListener(object: MovieAdapter.OnReachEndListener{
            override fun onReachEnd() {
                PAGE++
                presenter.getData(PAGE)
            }
        })

        adapter.setOnPosterClickListener(object: MovieAdapter.OnPosterClickListener{
            override fun onPosterClick(position: Int) {
                val intent = Intent(baseContext, DetailActivity::class.java)
                intent.putExtra("movieId", adapter.movies[position].id)
                startActivity(intent)
            }
        })
    }

    override fun showData(movies: List<Result>?) {
        adapter.addMovies(movies as ArrayList<Result>)
    }

    override fun showError() {
        Toast.makeText(this, "Load error!", Toast.LENGTH_SHORT)
    }

    fun onMenuClick(view: View){
        drawer_layout.openDrawer(GravityCompat.START)
    }
    fun onHomeClick(view: View){
        recreate()
        drawer_layout.closeDrawer(GravityCompat.START)
    }
    fun onFavouriteMoviesClick(view: View){
        val intent = Intent(this, FavouriteMoviesActivity::class.java)
        startActivity(intent)
        finish()
    }
}