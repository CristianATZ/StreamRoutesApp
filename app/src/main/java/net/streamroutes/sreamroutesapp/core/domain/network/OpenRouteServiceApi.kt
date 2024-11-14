package net.streamroutes.sreamroutesapp.core.domain.network

import net.streamroutes.sreamroutesapp.core.domain.model.OrsRouteResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Interfaz de la API de Open Route Service
 */
interface OpenRouteServiceApi {
    @GET("/v2/directions/driving-car")
    suspend fun getRoute(
        @Query("api_key") api_key: String,
        @Query("start") start: String,
        @Query("end") end: String
    ): Response<OrsRouteResponse>
}

object RetrofitORS {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.openrouteservice.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val retrofitService: OpenRouteServiceApi by lazy {
        retrofit.create(OpenRouteServiceApi::class.java)
    }
}