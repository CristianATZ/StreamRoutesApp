package net.streamroutes.sreamroutesapp.core.domain.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Configuración de retrofit para la API
 */
object OpenRouteServiceClient {
    private const val BASE_URL = "https://api.openrouteservice.org/"

    fun create(): OpenRouteServiceApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OpenRouteServiceApi::class.java)
    }
}