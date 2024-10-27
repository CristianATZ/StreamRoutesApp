package net.streamroutes.sreamroutesapp.core.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import net.streamroutes.sreamroutesapp.core.domain.model.Route

class RouteRepository {

    private val db = FirebaseFirestore.getInstance()

    fun getAllRoutes(): Flow<List<Route>> = callbackFlow {
        val listener = db.collection("routes")
            .addSnapshotListener { snapshot, e ->
                if(e != null){
                    close(e)
                    return@addSnapshotListener
                }

                val routes = snapshot?.documents?.mapNotNull { it.toObject(Route::class.java) }
                if(routes != null){
                    trySend(routes)
                }
            }

        awaitClose { listener.remove() }
    }
}