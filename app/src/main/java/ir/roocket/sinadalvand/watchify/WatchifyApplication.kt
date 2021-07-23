package ir.roocket.sinadalvand.watchify

import android.app.Application

class WatchifyApplication : Application() {

    val container = WatchifyContainer(this)
    
}