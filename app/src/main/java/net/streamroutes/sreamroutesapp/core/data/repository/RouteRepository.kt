package net.streamroutes.sreamroutesapp.core.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import net.streamroutes.sreamroutesapp.core.data.local.dao.PlaceDao
import net.streamroutes.sreamroutesapp.core.data.local.dao.RouteDao
import net.streamroutes.sreamroutesapp.core.data.local.dao.UserDao
import net.streamroutes.sreamroutesapp.core.data.local.entity.PlaceEntity
import net.streamroutes.sreamroutesapp.core.data.local.entity.RouteEntity
import net.streamroutes.sreamroutesapp.core.domain.model.Day
import net.streamroutes.sreamroutesapp.core.domain.model.Place
import net.streamroutes.sreamroutesapp.core.domain.model.Route
import net.streamroutes.sreamroutesapp.core.domain.model.RouteWithPlaces
import net.streamroutes.sreamroutesapp.core.domain.model.TuristicPoint
import net.streamroutes.sreamroutesapp.core.domain.model.TuristicPointWithInfo
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repositorio usado para Rutas de transporte público
 */

@Singleton
class RouteRepository @Inject constructor(
    private val db: FirebaseFirestore,
    private val routeDao: RouteDao,
    private val placeDao: PlaceDao
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
    suspend fun getAllTuristicPoints(): List<TuristicPointWithInfo> {
        return try {
            /*
            val turisticPoints = db.collection("places")
                .whereEqualTo("type", 1)
                .get()
                .await()
            turisticPoints.documents.mapNotNull { it.toObject(Place::class.java) }
             */
            val turisticPoints = mutableListOf<TuristicPointWithInfo>()
            val tpDocuments = db.collection("turisticPoints").get().await()

            for(tpInfo in tpDocuments.documents){
                val tp = tpInfo.toObject(TuristicPoint::class.java)

                if(tp != null){
                    val place = db.collection("places").document(tp.idPlace).get().await()
                    val placeObj = place.toObject(Place::class.java)

                    if(placeObj != null){
                        turisticPoints.add(
                            TuristicPointWithInfo(tp, placeObj)
                        )
                    }
                }
            }

            return turisticPoints
        } catch (e: Exception) {
            println("Error: ${e.message}")
            emptyList()
        }
    }


    /**
     * Método usado para obtener todas las rutas guardas en el
     * dispositivo
     */
    suspend fun getAllRoutesLocal(): List<RouteWithPlaces> {
        return try {
            val routes = routeDao.getAllRoutes()
            val routesWithPlaces = mutableListOf<RouteWithPlaces>()

            for(route in routes){
                val startPlace = placeDao.getPlace(route.idStartPlace)
                val endPlace = placeDao.getPlace(route.idEndPlace)

                if(startPlace != null){
                    val startPlaceLocal = Place(
                        idPlace = startPlace.idPlace,
                        name = startPlace.name,
                        latitude = startPlace.latitude,
                        longitude = startPlace.longitude,
                        state = startPlace.state,
                        street = startPlace.street,
                        suburb = startPlace.suburb,
                        type = startPlace.type,
                        imageUrl = startPlace.imageUrl
                    )

                    val endPlaceLocal = Place(
                        idPlace = endPlace.idPlace,
                        name = endPlace.name,
                        latitude = endPlace.latitude,
                        longitude = endPlace.longitude,
                        state = endPlace.state,
                        street = endPlace.street,
                        suburb = endPlace.suburb,
                        type = endPlace.type,
                        imageUrl = endPlace.imageUrl
                    )

                    val routeLocal = Route(
                        idRoute = route.idRoute,
                        idStartPlace = route.idStartPlace,
                        idEndPlace = route.idEndPlace,
                        name = route.name,
                        arriveTime = route.arriveTime,
                        noStops = route.noStops,
                        time = route.time
                    )

                    routesWithPlaces.add(
                        RouteWithPlaces(
                            routeLocal, startPlaceLocal, endPlaceLocal, emptyList()
                        )
                    )
                }
            }

            return routesWithPlaces
        } catch (e: Exception) {
            println("Error: ${e.message}")
            emptyList()
        }
    }


    /**
     * Método usado para guardar una ruta de transporte público en la base de datos
     * local de Room
     */
    suspend fun saveRoute(route: RouteWithPlaces): Boolean {
        return try {
            val existsRoute = routeDao.existsRoute(route.route.idRoute)

            if(existsRoute == 0){
                val routeRoom = RouteEntity(
                    idRoute = route.route.idRoute,
                    idEndPlace = route.route.idEndPlace,
                    idStartPlace = route.route.idStartPlace,
                    name = route.route.name,
                    arriveTime = route.route.arriveTime,
                    time = route.route.time,
                    noStops = route.route.noStops
                )
                routeDao.insertRoute(routeRoom)
            }

            val existsStartPlace = placeDao.existsPlace(route.startPlace.idPlace)
            if(existsStartPlace == 0){
                val startPlaceRoom = PlaceEntity(
                    idPlace = route.startPlace.idPlace,
                    name = route.startPlace.name,
                    latitude = route.startPlace.latitude,
                    longitude = route.startPlace.longitude,
                    state = route.startPlace.state,
                    street = route.startPlace.street,
                    suburb = route.startPlace.suburb,
                    type = route.startPlace.type
                )
                placeDao.insertPlace(startPlaceRoom)
            }

            val existsEndPlace = placeDao.existsPlace(route.endPlace.idPlace)
            if(existsEndPlace == 0){
                val endPlaceRoom = PlaceEntity(
                    idPlace = route.endPlace.idPlace,
                    name = route.endPlace.name,
                    latitude = route.endPlace.latitude,
                    longitude = route.endPlace.longitude,
                    state = route.endPlace.state,
                    street = route.endPlace.street,
                    suburb = route.endPlace.suburb,
                    type = route.endPlace.type
                )
                placeDao.insertPlace(endPlaceRoom)
            }

            return true
        } catch (e: Exception) {
            println("Error: ${e.message}")
            return false
        }
    }


}




