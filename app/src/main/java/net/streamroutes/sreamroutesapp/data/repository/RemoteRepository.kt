package net.streamroutes.sreamroutesapp.data.repository

import net.streamroutes.sreamroutesapp.data.ParkingService
import net.streamroutes.sreamroutesapp.data.container.AppContainer
import net.streamroutes.sreamroutesapp.data.model.ParkingResult

interface RemoteRepository {
    suspend fun getParkings(): ParkingResult
}

class NetworkRemoteReposiroty(
    private val parkingService: ParkingService
) : RemoteRepository {
    lateinit var container: AppContainer
    override suspend fun getParkings(): ParkingResult {
        return parkingService.getParkings()
    }

}