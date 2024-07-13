package net.ivanvega.milocationymapascompose.network

import android.location.Location
import net.ivanvega.milocationymapascompose.network.model.LocationPostItem
import net.ivanvega.milocationymapascompose.network.model.LocationResult
import net.ivanvega.milocationymapascompose.network.model.LocationResultItem
import retrofit2.Response

interface RemoteRepository {
    suspend fun getLastLocation(): Response<LocationResult>

    suspend fun insertLocation(location: LocationRequest): Response<LocationPostItem>
}

class NetworkRemoteReposiroty(
    private val orsService: ORService
) : RemoteRepository {

    override suspend fun getLastLocation(): Response<LocationResult> {
        return orsService.getLastLocation()
    }

    override suspend fun insertLocation(location: LocationRequest): Response<LocationPostItem> {
        return orsService.insertLocation(location)
    }
}