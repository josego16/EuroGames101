package com.eurogames

import android.app.Application
import com.eurogames.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class EuroGamesApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidLogger()
            androidContext(this@EuroGamesApp)
        }
    }
}