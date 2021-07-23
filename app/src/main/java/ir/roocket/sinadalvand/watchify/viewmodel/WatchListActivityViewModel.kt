package ir.roocket.sinadalvand.watchify.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ir.roocket.sinadalvand.watchify.data.model.Movie
import ir.roocket.sinadalvand.watchify.utils.MovieValue

class WatchListActivityViewModel(private val movieValue: MovieValue) : ViewModel() {

    val movies = MutableLiveData<ArrayList<Movie>>()

    fun getMovies() {
        movies.postValue(movieValue.getMovies())
    }

}