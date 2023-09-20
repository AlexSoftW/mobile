package com.application.sallus_app

import android.app.Application
import com.application.sallus_app.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

// Classe que extends Application.
// ela serve para ser inicializada antes de qualquer outra classe,
// para que a injeção de dependências possar injetar em todas classes e não só na MainActivity.

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(applicationContext)
            modules(appModule)
        }
    }
}