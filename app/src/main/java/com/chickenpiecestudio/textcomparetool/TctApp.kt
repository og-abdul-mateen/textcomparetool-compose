package com.chickenpiecestudio.textcomparetool

import android.app.Application
import com.chickenpiecestudio.textcomparetool.koinDi.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class TctApp: Application() {
    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin{
            androidLogger()
            androidContext(this@TctApp)
            modules(appModule)
        }
    }
}