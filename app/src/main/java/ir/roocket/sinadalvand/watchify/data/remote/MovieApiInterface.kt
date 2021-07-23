package ir.roocket.sinadalvand.watchify.data.remote

import ir.roocket.sinadalvand.watchify.data.model.Movie
import ir.roocket.sinadalvand.watchify.data.model.Paging
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiInterface {

    @GET("movies?")
    fun findMovie(@Query("q") name: String,@Query("page") page: Int = 1): Call<Paging<Movie>>

}