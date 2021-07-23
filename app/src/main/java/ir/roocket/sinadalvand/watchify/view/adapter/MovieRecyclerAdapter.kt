package ir.roocket.sinadalvand.watchify.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ir.roocket.sinadalvand.watchify.R
import ir.roocket.sinadalvand.watchify.data.model.Movie
import ir.roocket.sinadalvand.watchify.utils.CalendarUtils.toFormat
import kotlinx.android.synthetic.main.recycler_item_movie.view.*
import java.util.*

class MovieRecyclerAdapter() : RecyclerView.Adapter<MovieRecyclerAdapter.MovieHolder>() {


    private var selectListener: MovieSelectListener? = null

    private val data = arrayListOf<Movie>()

    private var selectedItem: Int = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        return MovieHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_item_movie, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        val movie = data[position]
        holder.setupMovie(movie)

        if (movie.watchedDate != null)
            holder.setTime(movie.watchedDate!!)

        if (selectedItem == position)
            holder.selectItem()
        else
            holder.unselect()

        if (selectListener != null)
            holder.iv.setOnClickListener {
                if (!movie.isSelected) {
                    if (selectedItem >= 0) {
                        unselect(selectedItem)
                    }
                    holder.selectItem()
                    selectListener?.onSelectMovie(movie)
                    selectedItem = position
                } else {
                    holder.unselect()
                    unselect(position)
                }
            }

    }

    private fun unselect(index: Int) {
        val movie = data[index]
        movie.isSelected = false
        notifyItemChanged(index)
        notifyDataSetChanged()
        selectedItem = -1
    }

    override fun getItemCount(): Int = data.size

    fun setMovies(movies: List<Movie>) {
        data.clear()
        data.addAll(movies)
        notifyItemMoved(0, data.size - 1)
        notifyDataSetChanged()
    }

    fun onMovieSelectListener(listener: MovieSelectListener?) {
        this.selectListener = listener
    }

    class MovieHolder(val iv: View) : RecyclerView.ViewHolder(iv) {

        var movie: Movie? = null

        val image = iv.recycler_item_movie_image
        val title = iv.recycler_item_movie_title
        val time = iv.recycler_item_movie_watched_time

        fun setupMovie(movie: Movie) {
            this.movie = movie
            Glide.with(iv).load(movie.poster).into(image)
            title.text = movie.title
        }

        fun setTime(time: Date) {
            this.time.visibility = View.VISIBLE
            this.time.text = time.toFormat()
        }

        fun selectItem() {
            image.borderColor = ContextCompat.getColor(iv.context, R.color.blue_cloud)
            movie?.isSelected = true
        }

        fun unselect() {
            image.borderColor = ContextCompat.getColor(iv.context, R.color.red_blood)
            movie?.isSelected = false
        }

    }


    interface MovieSelectListener {
        fun onSelectMovie(movie: Movie)
    }
}