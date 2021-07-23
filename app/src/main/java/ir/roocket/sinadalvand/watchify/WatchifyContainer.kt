package ir.roocket.sinadalvand.watchify

import android.content.Context
import com.google.gson.GsonBuilder
import ir.roocket.sinadalvand.watchify.data.remote.MovieApiInterface
import ir.roocket.sinadalvand.watchify.di.MovieAddFlow
import ir.roocket.sinadalvand.watchify.repository.MoviesSearchRepository
import ir.roocket.sinadalvand.watchify.utils.MovieValue
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WatchifyContainer(private val context: Context) {

    private val gson = GsonBuilder().create()
    private val sp = context.getSharedPreferences("app", Context.MODE_PRIVATE)
    private val retrofit = Retrofit.Builder().baseUrl("https://www.moviesapi.ir/api/v1/").addConverterFactory(GsonConverterFactory.create(gson)).build()
    private val api = retrofit.create(MovieApiInterface::class.java)

    val movieValue = MovieValue(gson, sp)
    val movieSearchRepo = MoviesSearchRepository(api)


    var movieAddFlow:MovieAddFlow? = null


}