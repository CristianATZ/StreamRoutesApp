package net.streamroutes.sreamroutesapp.data

import net.streamroutes.sreamroutesapp.data.model.parkinModel.ParkingResult
import net.streamroutes.sreamroutesapp.utils.Constants
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ParkingService {
    @GET("parking")
    suspend fun getParkings(): Response<ParkingResult>
}

object RetrofitParkingService {
    private val retrofit = Retrofit.Builder()
        .baseUrl(Constants.ParkingApi.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val retrofitService: ParkingService by lazy {
        retrofit.create(ParkingService::class.java)
    }
}