package com.example.marketnews

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class Application: Application() {

    companion object {
        private val TAG = Application::class.simpleName
    }

    override fun onCreate() {
        super.onCreate()

        Log.d(TAG, "NewsApplication created ")
    }
}