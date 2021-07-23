package ir.roocket.sinadalvand.watchify.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ir.roocket.sinadalvand.watchify.data.model.Movie
import ir.roocket.sinadalvand.watchify.data.model.Paging
import ir.roocket.sinadalvand.watchify.repository.MoviesSearchRepository
import ir.roocket.sinadalvand.watchify.utils.MovieValue
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WatchAddActivityViewModel(
    val moviesSearchRepository: MoviesSearchRepository,
    val movieValue: MovieValue
) : ViewModel() {

    val movies = MutableLiveData<List<Movie>?>()

    var selectedMovie: Movie? = null

    private var searched = ""

    fun searchMovie(name: String) {
        searched = name
        moviesSearchRepository.searchMovieOnline(name).enqueue(
            object : Callback<Paging<Movie>> {
                override fun onResponse(
                    call: Call<Paging<Movie>>,
                    response: Response<Paging<Movie>>
                ) {
                    if (searched == name)
                        movies.postValue(response.body()?.data ?: listOf())
                }

                override fun onFailure(call: Call<Paging<Movie>>, t: Throwable) {
                    movies.postValue(null)
                }

            }
        )
    }

    fun selectMovie(movie: Movie) {
        this.selectedMovie = movie
    }

    fun saveMovie(movie: Movie) {
        movieValue.save(movie)
    }

}