package com.example.kodecokmp.android

import android.app.Application
import com.example.kodecokmp.di.timezoneModule
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TimezoneApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@TimezoneApp)
            modules(timezoneModule())
        }
    }
}