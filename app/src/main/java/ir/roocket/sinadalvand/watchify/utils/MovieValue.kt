package ir.roocket.sinadalvand.watchify.utils

import android.content.SharedPreferences
import com.google.gson.Gson
import ir.roocket.sinadalvand.watchify.data.model.Movie

class MovieValue(private val gson: Gson,private val sharePref: SharedPreferences) {

    private val container = arrayListOf<Movie>()

    private val MOVIES_KEY = "MOVIES"

    /**
     * save movie as json into sharedPreference
     * @param movie Movie
     */
    fun save(movie: Movie) {
        readMovies()
        container.add(movie)
        val data = gson.toJson(container)
        sharePref.edit().putString(MOVIES_KEY, data).apply()
    }


    /**
     * return fresh list of watched movie
     * @return ArrayList<Movie>
     */
    fun getMovies(): ArrayList<Movie> {
        readMovies()
        return container
    }


    private fun readMovies() {
        container.clear()
        container.addAll(
            gson.fromJson(
                sharePref.getString(MOVIES_KEY, ""),
                Array<Movie>::class.java
            )?: arrayOf()
        );
    }


}