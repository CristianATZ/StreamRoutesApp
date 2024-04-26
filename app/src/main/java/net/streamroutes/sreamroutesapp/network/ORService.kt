package net.streamroutes.sreamroutesapp.network

import net.streamroutes.sreamroutesapp.model.Ruta
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ORService {
    @GET("/v2/directions/driving-car")
    suspend fun getRuta(
        @Query("api_key") key: String,
        @Query("start", encoded = true) start: String,
        @Query("end", encoded = true) end: String
    ): Response<Ruta>
}