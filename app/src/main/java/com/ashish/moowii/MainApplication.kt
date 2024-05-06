package com.ashish.moowii

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import timber.log.Timber.Forest.plant

@HiltAndroidApp
class MainApplication : Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: MainApplication? = null

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            plant(Timber.DebugTree())
        }
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
    }
}
