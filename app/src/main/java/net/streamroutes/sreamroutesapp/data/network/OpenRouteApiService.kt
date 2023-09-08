package net.streamroutes.sreamroutesapp.data.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import net.streamroutes.sreamroutesapp.model.Directions
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

//Sacar una cuenta en la paltaforma
//https://openrouteservice.org
private const val BASE_URL =
            "https://api.openrouteservice.org"

private const val API_KEY =
    "token"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(
        Json.asConverterFactory("application/json".toMediaType())
    )
    .baseUrl(BASE_URL)
    .build()

interface OpenRouteApiService {


    @GET("v2/directions/{profile}")
    suspend fun directionsget(@Path("profile") profile : String,
                               @Query ("api_key") key: String ,
                              @Query ("start")  start: String ,
                              @Query ("end")  end: String
                              ) : Directions

}