package ir.roocket.sinadalvand.watchify.di

import ir.roocket.sinadalvand.watchify.utils.MovieValue
import ir.roocket.sinadalvand.watchify.viewmodel.WatchListActivityViewModel

class WatchListViewModelFactory(val movieValue: MovieValue) : Factory<WatchListActivityViewModel> {

    override fun create(): WatchListActivityViewModel = WatchListActivityViewModel(movieValue)
}