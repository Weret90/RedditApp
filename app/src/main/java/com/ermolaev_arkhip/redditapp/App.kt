package com.ermolaev_arkhip.redditapp

import android.app.Application
import com.ermolaev_arkhip.redditapp.di.DataModule
import com.ermolaev_arkhip.redditapp.di.PresentationModule
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(DataModule.value, PresentationModule.value)
        }
    }
}