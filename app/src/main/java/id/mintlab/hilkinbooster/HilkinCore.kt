package id.mintlab.hilkinbooster

import android.app.Application

class HilkinCore : Application() {

    companion object Constants {
        const val TAG = "HilkinBooster"
    }

    override fun onCreate() {
        super.onCreate()
        ObjectBox.init(this)
    }

}