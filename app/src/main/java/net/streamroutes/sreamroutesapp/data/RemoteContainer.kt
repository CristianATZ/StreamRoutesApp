package net.streamroutes.sreamroutesapp.data

import android.app.Application
import android.content.Context

class RemoteContainer : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(this)
    }

    init {
        instance = this
    }

    fun getAppContainer():AppContainer {
        return container
    }

    companion object {
        private var instance: RemoteContainer ?= null

        // funciones de base de datos
    }
}