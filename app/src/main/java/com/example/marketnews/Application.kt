package com.example.marketnews

import android.app.Application
import android.util.Log
import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class Application: MultiDexApplication() {

    companion object {
        private val TAG = Application::class.simpleName
    }

    override fun onCreate() {
        super.onCreate()

        Log.d(TAG, "NewsApplication created ")
    }
}