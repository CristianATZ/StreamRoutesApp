package net.streamroutes.sreamroutesapp.core.data.repository

import android.util.Log
import net.streamroutes.sreamroutesapp.core.domain.model.OrsPlannerRequestBody
import net.streamroutes.sreamroutesapp.core.domain.model.OrsPlannerResponse
import net.streamroutes.sreamroutesapp.core.domain.model.OrsRouteResponse
import net.streamroutes.sreamroutesapp.core.domain.network.OpenRouteServiceApi
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class OrsRepository @Inject constructor (
    private val orsService: OpenRouteServiceApi
) {
    val apiKey = "5b3ce3597851110001cf6248cf096e9bff7543a9b65bfeea90be20ac"

    suspend fun fetchRoute(start: String, end: String): Response<OrsRouteResponse> {
        return orsService.getRoute(apiKey, start, end)
    }

    suspend fun getAddress(longitude: String, latitude: String): Response<OrsPlannerResponse> {
        return orsService.getAddress(apiKey, longitude, latitude)
    }

    suspend fun planRoute(coordinates: List<List<Double>>): Response<OrsRouteResponse> {
        val routeRequestBody = OrsPlannerRequestBody(coordinates = coordinates)
        return orsService.planRoute(apiKey = apiKey, body = routeRequestBody)
    }
}