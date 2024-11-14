package net.streamroutes.sreamroutesapp.core.domain.network

import net.streamroutes.sreamroutesapp.core.domain.model.OrsRouteResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

/**
 * Interfaz de la API de Open Route Service
 */
interface OpenRouteServiceApi {
    @GET("/v2/directions/driving-car")
    suspend fun getRoute(
        @Header("Authorization") apiKey: String,
        @Query("start") start: String,
        @Query("end") end: String
    ): OrsRouteResponse
}