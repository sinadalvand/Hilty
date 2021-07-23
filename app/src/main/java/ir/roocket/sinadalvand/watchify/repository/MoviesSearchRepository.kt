package ir.roocket.sinadalvand.watchify.repository

import ir.roocket.sinadalvand.watchify.data.model.Movie
import ir.roocket.sinadalvand.watchify.data.model.Paging
import ir.roocket.sinadalvand.watchify.data.remote.MovieApiInterface
import retrofit2.Call
import retrofit2.Callback

class MoviesSearchRepository(private val movieApiInterface: MovieApiInterface) {

    fun searchMovieOnline(name: String): Call<Paging<Movie>> {
        return movieApiInterface.findMovie(name)
    }

}