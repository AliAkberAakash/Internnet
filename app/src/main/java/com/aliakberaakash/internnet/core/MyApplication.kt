package com.aliakberaakash.internnet.core

import android.app.Application
import com.aliakberaakash.internnet.BuildConfig
import timber.log.Timber

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}