package net.streamroutes.sreamroutesapp.core.data.repository

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import net.streamroutes.sreamroutesapp.core.domain.model.Parking
import net.streamroutes.sreamroutesapp.core.domain.model.Place
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ParkingRepository @Inject constructor(
    private val db: FirebaseFirestore
) {
    /**
     * Método usado para obtener todos los estacionamientos de la base de datos
     */
    suspend fun getAllParkings(): List<ParkingWithPlace> {
        return try {
            val parkings = mutableListOf<ParkingWithPlace>()
            val parkingsDocs = db.collection("parkings").get().await()
            Log.d("parking_repo", parkingsDocs.toString())
            Log.d("parking_repo", parkings.toString())

            for(document in parkingsDocs){
                val parkingObj = document.toObject(Parking::class.java)

                if(parkingObj != null){
                    val placeInfo = db.collection("places").document(parkingObj.idPlace).get().await()
                    val placeObj = placeInfo.toObject(Place::class.java)

                    if(placeObj != null){
                        parkings.add(
                            ParkingWithPlace(parkingObj, placeObj)
                        )
                    }
                }
            }



            return parkings
        } catch (e: Exception) {
            Log.d("parking_repo", "Error: ${e.message}")
            println("Error: ${e.message}")
            emptyList()
        }
    }
}


data class ParkingWithPlace(
    val parking: Parking,
    val place: Place
)