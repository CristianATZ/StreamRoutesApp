package net.streamroutes.sreamroutesapp.data

interface OfflineRepository {

}

class NetworkOfflineRepository() : OfflineRepository {
    lateinit var container: AppContainer

}