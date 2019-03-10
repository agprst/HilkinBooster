package id.mintlab.hilkinbooster

import android.content.Context
import android.util.Log
import io.objectbox.BoxStore


object ObjectBox {
    private var boxStore: BoxStore? = null

    fun init(context: Context) {
        boxStore = MyObjectBox.builder()
            .androidContext(context.getApplicationContext())
            .build()

        if (BuildConfig.DEBUG) {
            Log.d(HilkinCore.TAG, "Using ObjectBox ${BoxStore.getVersion()} (${BoxStore.getVersionNative()})")
        }
    }

    fun get(): BoxStore? {
        return boxStore
    }
}