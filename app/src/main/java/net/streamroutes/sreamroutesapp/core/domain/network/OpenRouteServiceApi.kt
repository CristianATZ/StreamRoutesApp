package net.streamroutes.sreamroutesapp.core.domain.network

import net.streamroutes.sreamroutesapp.core.domain.model.OrsPlannerResponse
import net.streamroutes.sreamroutesapp.core.domain.model.OrsRouteRequestBody
import net.streamroutes.sreamroutesapp.core.domain.model.OrsRouteResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Interfaz de la API de Open Route Service
 */
interface OpenRouteServiceApi {
    // Función para obtener una ruta de un coche
    @GET("/v2/directions/driving-car")
    suspend fun getRoute(
        @Query("api_key") apiKey: String,
        @Query("start") start: String,
        @Query("end") end: String
    ): Response<OrsRouteResponse>

    // Función para obtener la dirección de un LatLng
    @GET("geocode/reverse")
    suspend fun getAddress(
        @Query("api_key") apiKey: String,
        @Query("point.lon") longitude: String,
        @Query("point.lat") latitude: String,
        @Query("size") size: String = "1",
    ): Response<OrsPlannerResponse>

    // Para planifica tu viaje, se usa la funcion getRoute pero con un body y header
    @POST("/v2/directions/driving-car")
    suspend fun planRoute(
        @Header("Authorization") apiKey: String,
        @Header("Accept") accept: String = "application/json, application/geo+json, application/gpx+xml, img/png; charset=utf-8",
        @Header("Content-Type") contentType: String = "application/json",
        @Body body: OrsRouteRequestBody
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