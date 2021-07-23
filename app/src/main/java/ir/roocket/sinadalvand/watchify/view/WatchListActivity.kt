package ir.roocket.sinadalvand.watchify.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ir.roocket.sinadalvand.watchify.R
import ir.roocket.sinadalvand.watchify.data.model.Movie
import ir.roocket.sinadalvand.watchify.view.adapter.MovieRecyclerAdapter
import kotlinx.android.synthetic.main.activity_watchlist.*
import kotlinx.android.synthetic.main.fragment_search.*

class WatchListActivity : AppCompatActivity() {

    private lateinit var adapter:MovieRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_watchlist)

        activity_watchlist_add.setOnClickListener {
            startActivity(Intent(this, WatchAddActivity::class.java))
        }

        setupRecycler()


        // TODO provide viewModel dependency

    }

    private fun setupRecycler(){
        adapter = MovieRecyclerAdapter()
        activity_watchlist_recycler.adapter = adapter
    }


    override fun onResume() {
        super.onResume()
        //TODO request to get movies from ViewModel
    }

    private fun setupMovies(movies: ArrayList<Movie>) {
        adapter.setMovies(movies.toList())
    }

}