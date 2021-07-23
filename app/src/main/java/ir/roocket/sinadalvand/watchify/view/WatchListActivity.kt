package ir.roocket.sinadalvand.watchify.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.gson.GsonBuilder
import ir.roocket.sinadalvand.watchify.R
import ir.roocket.sinadalvand.watchify.WatchifyApplication
import ir.roocket.sinadalvand.watchify.data.model.Movie
import ir.roocket.sinadalvand.watchify.utils.MovieValue
import ir.roocket.sinadalvand.watchify.view.adapter.MovieRecyclerAdapter
import ir.roocket.sinadalvand.watchify.viewmodel.WatchListActivityViewModel
import kotlinx.android.synthetic.main.activity_watchlist.*
import kotlinx.android.synthetic.main.fragment_search.*

class WatchListActivity : AppCompatActivity() {

    private lateinit var adapter: MovieRecyclerAdapter

    private lateinit var model: WatchListActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_watchlist)

        activity_watchlist_add.setOnClickListener {
            startActivity(Intent(this, WatchAddActivity::class.java))
        }

        setupRecycler()


        val container = (application as WatchifyApplication).container

        model = WatchListActivityViewModel(container.movieValue)


        model.movies.observe(this){
            setupMovies(it)
        }

    }

    private fun setupRecycler() {
        adapter = MovieRecyclerAdapter()
        activity_watchlist_recycler.adapter = adapter
    }


    override fun onResume() {
        super.onResume()
        model.getMovies()
    }

    private fun setupMovies(movies: ArrayList<Movie>) {
        adapter.setMovies(movies.toList())
    }

}