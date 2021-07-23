package ir.roocket.sinadalvand.watchify.view.frag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.gson.GsonBuilder
import ir.roocket.sinadalvand.watchify.R
import ir.roocket.sinadalvand.watchify.data.model.Movie
import ir.roocket.sinadalvand.watchify.data.remote.MovieApiInterface
import ir.roocket.sinadalvand.watchify.repository.MoviesSearchRepository
import ir.roocket.sinadalvand.watchify.view.WatchAddActivity
import ir.roocket.sinadalvand.watchify.view.adapter.MovieRecyclerAdapter
import ir.roocket.sinadalvand.watchify.viewmodel.WatchAddActivityViewModel
import ir.roocket.sinadalvand.watchify.viewmodel.WatchListActivityViewModel
import kotlinx.android.synthetic.main.fragment_search.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchFragment : Fragment(), MovieRecyclerAdapter.MovieSelectListener {

    lateinit var adapter: MovieRecyclerAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setupRecycler()


        // TODO make ViewModel to request to "https://www.moviesapi.ir/api/v1/"

        fragment_search_input.doOnTextChanged { text, _, _, _ ->
            if ((text?.length ?: 0) > 3){
                // TODO call search function into ViewModel
            }

        }

        //TODO observe movies into Viewmodel to call SetupMovies()

    }

    private fun setupRecycler(){
        adapter = MovieRecyclerAdapter()
        adapter.onMovieSelectListener(this)
        fragment_search_recycler.adapter = adapter
    }

    private fun setupMovies(movies: List<Movie>) {
        adapter.setMovies(movies)
    }

    override fun onSelectMovie(movie: Movie) {
        // TODO set selected movie into Viewmodel
        findNavController().navigate(R.id.dateFragment)
    }
}