package net.streamroutes.sreamroutesapp.data

import net.streamroutes.sreamroutesapp.data.model.orsModel.RouteResult
import net.streamroutes.sreamroutesapp.utils.Constants
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


// https://api.openrouteservice.org/v2/directions/driving-car
interface ORService {
    @GET("/v2/directions/driving-car")
    suspend fun getRuta(
        @Query("api_key") key: String,
        @Query("start") start: String,
        @Query("end") end: String
    ): Response<RouteResult>
}

object RetrofitOpenRouteService {
    private val retrofit = Retrofit.Builder()
        .baseUrl(Constants.ORsApi.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val retrofitService: ORService by lazy {
        retrofit.create(ORService::class.java)
    }
}