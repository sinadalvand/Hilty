package ir.roocket.sinadalvand.watchify.view.frag

import android.content.Context
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
import ir.roocket.sinadalvand.watchify.utils.MovieValue
import ir.roocket.sinadalvand.watchify.view.WatchAddActivity
import ir.roocket.sinadalvand.watchify.view.adapter.MovieRecyclerAdapter
import ir.roocket.sinadalvand.watchify.viewmodel.WatchAddActivityViewModel
import ir.roocket.sinadalvand.watchify.viewmodel.WatchListActivityViewModel
import kotlinx.android.synthetic.main.fragment_search.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchFragment : Fragment(), MovieRecyclerAdapter.MovieSelectListener {

    lateinit var adapter: MovieRecyclerAdapter

    lateinit var model: WatchAddActivityViewModel

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

        val gson = GsonBuilder().create()

        val sp = requireContext().getSharedPreferences("app", Context.MODE_PRIVATE)
        val movieValue = MovieValue(gson, sp)


        val retrofit = Retrofit.Builder().baseUrl("https://www.moviesapi.ir/api/v1/")
            .addConverterFactory(GsonConverterFactory.create(gson)).build()
        val api = retrofit.create(MovieApiInterface::class.java)
        val repo = MoviesSearchRepository(api)
        model = WatchAddActivityViewModel(repo,movieValue)



        fragment_search_input.doOnTextChanged { text, _, _, _ ->
            if ((text?.length ?: 0) > 3) {
               model.searchMovie(text.toString())
            }

        }

        model.movies.observe(viewLifecycleOwner){
            if(it!=null)
                setupMovies(it)
        }

    }

    private fun setupRecycler() {
        adapter = MovieRecyclerAdapter()
        adapter.onMovieSelectListener(this)
        fragment_search_recycler.adapter = adapter
    }

    private fun setupMovies(movies: List<Movie>) {
        adapter.setMovies(movies)
    }

    override fun onSelectMovie(movie: Movie) {
        model.selectedMovie = movie
        findNavController().navigate(R.id.dateFragment)
    }
}