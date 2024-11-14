package net.streamroutes.sreamroutesapp.core.data.repository

import android.util.Log
import net.streamroutes.sreamroutesapp.core.domain.model.OrsRouteResponse
import net.streamroutes.sreamroutesapp.core.domain.network.OpenRouteServiceApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OrsRepository @Inject constructor (
    private val api: OpenRouteServiceApi
) {
    suspend fun fetchRoute(start: String, end: String): OrsRouteResponse {
        val response = api.getRoute("5b3ce3597851110001cf6248cf096e9bff7543a9b65bfeea90be20ac", start, end)
        Log.d("RESPONSE_BODY", "Start: $start, End: $end")
        Log.d("RESPONSE_BODY", response.toString()) // Para ver el cuerpo de la respuesta en bruto
        return response
        //return api.getRoute("5b3ce3597851110001cf6248cf096e9bff7543a9b65bfeea90be20ac", start, end)
    }
}