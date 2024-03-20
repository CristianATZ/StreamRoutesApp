package net.streamroutes.sreamroutesapp.data

interface RemoteRepository {

}

class NetworkRemoteReposiroty() : RemoteRepository {
    lateinit var container: AppContainer

}