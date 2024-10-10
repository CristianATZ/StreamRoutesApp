package net.streamroutes.sreamroutesapp.data.repository

import net.streamroutes.sreamroutesapp.data.ORService
import net.streamroutes.sreamroutesapp.data.ParkingService
import net.streamroutes.sreamroutesapp.data.model.ors.RouteResult
import net.streamroutes.sreamroutesapp.data.model.parkings.ParkingResult
import net.streamroutes.sreamroutesapp.utils.Constants
import retrofit2.Response

interface RemoteRepository {
    suspend fun getParkings(): Response<ParkingResult>

    suspend fun getBestRoute(start: String, end: String): Response<RouteResult>
}

class NetworkRemoteReposiroty(
    private val parkingService: ParkingService,
    private val orsService: ORService
) : RemoteRepository {

    override suspend fun getParkings(): Response<ParkingResult> {
        return parkingService.getParkings()
    }

    override suspend fun getBestRoute(start: String, end: String): Response<RouteResult> {
        return orsService.getRuta(Constants.ORsApi.apiKey, start, end)
    }
}