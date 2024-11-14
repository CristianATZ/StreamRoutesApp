package net.streamroutes.sreamroutesapp.core.data.repository

import android.util.Log
import net.streamroutes.sreamroutesapp.core.domain.model.OrsRouteResponse
import net.streamroutes.sreamroutesapp.core.domain.network.OpenRouteServiceApi
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class OrsRepository @Inject constructor (
    private val orsService: OpenRouteServiceApi
) {
    suspend fun fetchRoute(start: String, end: String): Response<OrsRouteResponse> {
        return orsService.getRoute("5b3ce3597851110001cf6248cf096e9bff7543a9b65bfeea90be20ac", start, end)
    }
}