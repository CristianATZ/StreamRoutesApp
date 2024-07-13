package net.ivanvega.milocationymapascompose.network

import net.ivanvega.milocationymapascompose.network.model.LocationPostItem
import net.ivanvega.milocationymapascompose.network.model.LocationResult
import net.ivanvega.milocationymapascompose.network.model.LocationResultItem
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

data class LocationRequest(val latitud: Double, val longitud: Double)

interface ORService {
    @GET("getLastLocation")
    suspend fun getLastLocation(): Response<LocationResult>

    @Headers("Content-Type:application/json")
    @POST("insertLocation")
    suspend fun insertLocation(@Body location: LocationRequest): Response<LocationPostItem>
}

object RetrofitOpenRouteService {
    private val retrofit = Retrofit.Builder()
            .baseUrl("http://48.216.217.40:3000/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val retrofitService: ORService by lazy {
        retrofit.create(ORService::class.java)
    }
}