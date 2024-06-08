package net.streamroutes.sreamroutesapp.data.container

import android.content.Context
import net.streamroutes.sreamroutesapp.data.repository.NetworkOfflineRepository
import net.streamroutes.sreamroutesapp.data.repository.OfflineRepository

interface AppContainer {
    // hola alan
    val offlineRepository : OfflineRepository
    //val remoteRepository : RemoteRepository
}

class DefaultAppContainer(private val context: Context) : AppContainer {

    override val offlineRepository: OfflineRepository by lazy {
        NetworkOfflineRepository()
    }

    /*override val remoteRepository: RemoteRepository by lazy {
        NetworkRemoteReposiroty()
    }*/

    /*override val workerRepository: WorkerRepository by lazy {
        WorkerSupervisor(context)
    }*/

}