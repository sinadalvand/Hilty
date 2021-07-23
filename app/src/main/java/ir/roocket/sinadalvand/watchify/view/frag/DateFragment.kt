package ir.roocket.sinadalvand.watchify.view.frag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.ozcanalasalvar.library.view.datePicker.DatePicker
import ir.roocket.sinadalvand.watchify.R
import ir.roocket.sinadalvand.watchify.data.model.Movie
import ir.roocket.sinadalvand.watchify.utils.CalendarUtils.toDate
import ir.roocket.sinadalvand.watchify.view.WatchAddActivity
import kotlinx.android.synthetic.main.fragment_date.*
import kotlinx.android.synthetic.main.recycler_item_movie.*

class DateFragment : Fragment(), DatePicker.DataSelectListener {

    private var movie: Movie? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_date, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupDatePicker()

        fragment_date_submit.setOnClickListener {
            saveMovie(movie!!)
        }

        // TODO get movie from viewModel to setup


        if (movie != null)
            setupMovie(movie!!)


        // TODO provide dependency to save movie into MovieValue by ViewModel


    }

    private fun setupDatePicker() {
        fragment_date_timepicker.isDarkModeEnabled = true
        fragment_date_timepicker.setDataSelectListener(this)
    }

    private fun setupMovie(movie: Movie) {
        Glide.with(requireActivity()).load(movie.poster).into(recycler_item_movie_image)
        recycler_item_movie_title.text = movie.title
    }

    private fun saveMovie(movie: Movie) {
        movie.watchedDate = fragment_date_timepicker.date.toDate()
        movie.let {
            //TODO save movie into MovieValue by ViewModel
        }
        requireActivity().finish()
    }

    override fun onDateSelected(date: Long, day: Int, month: Int, year: Int) {
        recycler_item_movie_watched_time.visibility = View.VISIBLE
        recycler_item_movie_watched_time.text = " ${day}/${month}/${year}"
    }
}