package ir.roocket.sinadalvand.watchify.di

import ir.roocket.sinadalvand.watchify.repository.MoviesSearchRepository
import ir.roocket.sinadalvand.watchify.utils.MovieValue
import ir.roocket.sinadalvand.watchify.viewmodel.WatchAddActivityViewModel

class MovieAddFlow(
    val moviesSearchRepository: MoviesSearchRepository,
    val movieValue: MovieValue
) {

    val watchAddActivityViewModel = WatchAddActivityViewModel(moviesSearchRepository,movieValue)

    // ...
}