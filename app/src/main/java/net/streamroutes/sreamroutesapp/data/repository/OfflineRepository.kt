package net.streamroutes.sreamroutesapp.data.repository

import net.streamroutes.sreamroutesapp.data.container.AppContainer

interface OfflineRepository {

}

class NetworkOfflineRepository() : OfflineRepository {
    lateinit var container: AppContainer

}