package net.streamroutes.sreamroutesapp.core.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import net.streamroutes.sreamroutesapp.core.domain.model.Place
import net.streamroutes.sreamroutesapp.core.domain.model.Route
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RouteRepository @Inject constructor(
    private val db: FirebaseFirestore
) {
    /**
     * Método usado para obtener todas las rutas de transporte público
     */
    suspend fun getAllRoutes(): List<RouteWithPlaces>{
        return try {
            val routes = mutableListOf<RouteWithPlaces>()
            val routeDocuments = db.collection("routes").get().await()

            // iterar sobre las rutas obtenidas de la db
            for(document in routeDocuments.documents){
                val route = document.toObject(Route::class.java)

                if(route != null){
                    // traer punto de partida y destino de cada ruta con sus id's
                    val startPlace = db.collection("places").document(route.idStartPlace).get().await()
                    val endPlace = db.collection("places").document(route.idEndPlace).get().await()

                    // convertir los lugares a objetos tipo Place
                    val startPlaceObj = startPlace.toObject(Place::class.java)
                    val endPlaceObj = endPlace.toObject(Place::class.java)

                    val turisticPlaces = mutableListOf<Place>()
                    for (turisticPointId in route.turisticPoints) {
                        val turisticPlaceDoc = db.collection("places").document(turisticPointId).get().await()
                        val turisticPlaceObj = turisticPlaceDoc.toObject(Place::class.java)
                        if (turisticPlaceObj != null) {
                            turisticPlaces.add(turisticPlaceObj)
                        }
                    }

                    // guardar la ruta completa en el arreglo
                    if(startPlaceObj != null && endPlaceObj != null){
                        routes.add(
                            RouteWithPlaces(route, startPlaceObj, endPlaceObj, turisticPlaces)
                        )
                    }
                }
            }
            return routes
        } catch (e: Exception) {
            println("Error: ${e.message}")
            emptyList()
        }
    }


    /**
     * Método usado para obtener todos los puntos turísticos registrados en la bd
     */
    suspend fun getAllTuristicPoints(): List<Place> {
        return try {
            val turisticPoints = db.collection("places")
                .whereEqualTo("type", 1)
                .get()
                .await()
            turisticPoints.documents.mapNotNull { it.toObject(Place::class.java) }
        } catch (e: Exception) {
            println("Error: ${e.message}")
            emptyList()
        }
    }
}


data class RouteWithPlaces(
    val route: Route,
    val startPlace: Place,
    val endPlace: Place,
    val turisticPoint: List<Place>
)