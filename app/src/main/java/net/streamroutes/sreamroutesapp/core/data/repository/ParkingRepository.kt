package net.streamroutes.sreamroutesapp.core.data.repository

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.tasks.await
import net.streamroutes.sreamroutesapp.core.domain.model.HistoricalParking
import net.streamroutes.sreamroutesapp.core.domain.model.HistoricalParkingWithInfo
import net.streamroutes.sreamroutesapp.core.domain.model.Parking
import net.streamroutes.sreamroutesapp.core.domain.model.ParkingWithPlace
import net.streamroutes.sreamroutesapp.core.domain.model.Place
import net.streamroutes.sreamroutesapp.core.domain.model.ReservationParking
import net.streamroutes.sreamroutesapp.core.domain.model.ReservationWithInfo
import net.streamroutes.sreamroutesapp.core.domain.model.Service
import net.streamroutes.sreamroutesapp.core.domain.model.User
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.random.Random

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
            //Log.d("parking_repo", parkingsDocs.toString())
            //Log.d("parking_repo", parkings.toString())

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

    /**
     * Método usado para obtener todos los servicios de un estacionamiento
     */
    suspend fun getServicesByParking(idParking: String): List<String> {
        return try {
            // Obtener los servicios correspondientes del estacionamiento
            val parkServDocs = db.collection("parking_service")
                .whereEqualTo("idParking", idParking)
                .get()
                .await()

            // Obteer todos los servicios de la DB y aosciarlos a un map junto con su ID de servicio
            val serviceDocs = db.collection("services").get().await()
            val mapServices = serviceDocs.documents.associate {
                it.id to it.getString("description").orEmpty()
            }

            // Obtener description de cda servicio de cada estacionamiento
            return parkServDocs.documents.mapNotNull {
                val idService = it.getString("idService")
                mapServices[idService]
            }

        } catch (e: Exception) {
            Log.d("parking_repo", "Error: ${e.message}")
            println("Error: ${e.message}")
            emptyList()
        }
    }


    /**
     * Método usado para obtener el historial de aparcamientos de un usuario
     */
    suspend fun getHistoricalParkingByUser(idUser: String): List<HistoricalParkingWithInfo>{
        return try {
            val historical = mutableListOf<HistoricalParkingWithInfo>()
            val historicalDocs = db.collection("historicalParkings")
                .whereEqualTo("idUser", idUser)
                .get()
                .await()

            for(document in historicalDocs){
                val historicalObj = document.toObject(HistoricalParking::class.java)

                if(historicalObj != null){
                    val placeInfo = db.collection("places").document(historicalObj.idPlace).get().await()
                    val userInfo = db.collection("users").document(historicalObj.idUser).get().await()

                    val placeObj = placeInfo.toObject(Place::class.java)
                    val userObj = userInfo.toObject(User::class.java)

                    if(placeObj!=null && userObj!=null){
                        historical.add(
                            HistoricalParkingWithInfo(historicalObj, placeObj, userObj)
                        )
                    }
                }

            }

            return historical

        } catch (e: Exception) {
            //Log.d("parking_repo", "Error: ${e.message}")
            println("Error: ${e.message}")
            emptyList()
        }
    }


    /**
     * Método usado para obtener las reservaciones de aparcamientos de un usuario
     */
    suspend fun getReservationByUser(idUser: String): List<ReservationWithInfo>{
        return try {
            val reservations = mutableListOf<ReservationWithInfo>()
            val reservationDocs = db.collection("reservationParkings")
                .whereEqualTo("idUser", idUser)
                .get()
                .await()

            for(document in reservationDocs){
                val reservationObj = document.toObject(ReservationParking::class.java)

                if(reservationObj != null){
                    val parkingInfo = db.collection("parkings").document(reservationObj.idParking).get().await()
                    val parkingObj = parkingInfo.toObject(Parking::class.java)

                    val placeInfo =
                        parkingObj?.let { db.collection("places").document(it.idPlace).get().await() }
                    val placeObj = placeInfo?.toObject(Place::class.java)

                    val userInfo = db.collection("users").document(reservationObj.idUser).get().await()
                    val userObj = userInfo.toObject(User::class.java)

                    if(parkingObj!=null && placeObj!=null && userObj!=null){
                        reservations.add(
                            ReservationWithInfo(
                                reservationObj, parkingObj, placeObj, userObj
                            )
                        )
                    }
                }

            }

            return reservations
        } catch (e: Exception) {
            //Log.d("parking_repo", "Error: ${e.message}")
            println("Error: ${e.message}")
            emptyList()
        }
    }


    /**
     * Método usado para crear una reservación
     */
    suspend fun createReservation(reservation: ReservationParking): Boolean {
        return try {
            val newReservation = db.collection("reservationParkings").document()
            reservation.reference = generateReference()
            newReservation.set(reservation).await()
            true
        } catch (e: Exception) {
            println("Error: ${e.message}")
            false
        }
    }


    private fun generateReference(): String {
        val randomDigits = Random.nextInt(0, 10000).toString().padStart(4, '0')
        return "RES-${randomDigits}"
    }
}

