package ir.roocket.sinadalvand.watchify

import android.app.Application

class WatchifyApplication : Application() {

    lateinit var container:WatchifyContainer


    override fun onCreate() {
        super.onCreate()
        container = WatchifyContainer(this)
    }
}