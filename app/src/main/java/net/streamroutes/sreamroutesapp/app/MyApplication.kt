package net.streamroutes.sreamroutesapp.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {
    // maybe aqui inicializar firebase, room, etc


    override fun onCreate() {
        super.onCreate()
    }
}