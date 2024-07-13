/*
package net.ivanvega.milocationymapascompose.workmanager

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import net.ivanvega.milocationymapascompose.network.LocationRequest
import net.ivanvega.milocationymapascompose.network.NetworkRemoteReposiroty
import net.ivanvega.milocationymapascompose.network.RetrofitOpenRouteService
import java.util.concurrent.TimeUnit

class LocationWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    private val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)
    private val repo: NetworkRemoteReposiroty = NetworkRemoteReposiroty(RetrofitOpenRouteService.retrofitService)

    @SuppressLint("MissingPermission")
    override fun doWork(): Result {
        Log.d("LocationWorker", "Worker ejecutado")

        val nextRequest = OneTimeWorkRequestBuilder<LocationWorker>()
            .setInitialDelay(10, TimeUnit.SECONDS)
            .addTag("LocationWork")
            .build()
        WorkManager.getInstance(applicationContext).enqueue(nextRequest)

        return try {
            runBlocking(Dispatchers.IO) {
                val location: Location? = fusedLocationClient.lastLocation.await()
                if (location != null) {
                    Log.d("LocationWorker", "Ubicación obtenida: ${location.latitude}, ${location.longitude}")
                    sendLocationToApi(location)
                    Result.success()
                } else {
                    Log.d("LocationWorker", "No se pudo obtener la ubicación")
                    Result.failure()
                }
            }
        } catch (e: Exception) {
            Log.e("LocationWorker", "Error al obtener la ubicación o enviar a la API", e)
            Result.failure()
        }
    }

    private suspend fun sendLocationToApi(location: Location) {
        val locationRequest = LocationRequest(location.latitude, location.longitude)
        val response = repo.insertLocation(locationRequest)
        if (response.isSuccessful) {
            Log.d("LocationWorker", "Ubicación enviada con éxito: ${response}")
        } else {
            Log.d("LocationWorker", "Error al enviar la ubicación: ${response.errorBody()}")
        }
    }
}*/
