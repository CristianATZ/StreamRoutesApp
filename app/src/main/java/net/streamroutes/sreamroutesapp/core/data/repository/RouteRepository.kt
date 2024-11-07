package net.streamroutes.sreamroutesapp.core.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import net.streamroutes.sreamroutesapp.core.domain.model.Place
import net.streamroutes.sreamroutesapp.core.domain.model.Route

class RouteRepository(
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
) {
    /**
     * Método usado para obtener todas las rutas de transporte público
     */
    suspend fun getAllRoutes(): List<RouteWithPlaces>{
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

                // guardar la ruta completa en el arreglo
                if(startPlaceObj != null && endPlaceObj != null){
                    routes.add(
                        RouteWithPlaces(route, startPlaceObj, endPlaceObj)
                    )
                }
            }
        }

        return routes
    }
}


data class RouteWithPlaces(
    val route: Route,
    val startPlace: Place,
    val endPlace: Place
)