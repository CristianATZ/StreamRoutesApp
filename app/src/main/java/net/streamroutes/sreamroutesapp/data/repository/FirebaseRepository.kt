package net.streamroutes.sreamroutesapp.data.repository

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import net.streamroutes.sreamroutesapp.data.model.routes.Route

class FirebaseRepository {
    // TIEMPO REAL
    private val database = FirebaseDatabase
        .getInstance("https://rumapp-api-default-rtdb.firebaseio.com/")
        .getReference("coordinates")

    fun insertLocation(data: Route, onSuccess: () -> Unit, onFailure: (Exception) -> Unit){
        database.push().setValue(data)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onFailure(it) }
    }

    fun getLocation(onDataChanged: (List<Route>) -> Unit){
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d("Firebase", "Datos obtenidos: ${snapshot.value}")
                val routeList = mutableListOf<Route>()
                for(dataSnapshot in snapshot.children){
                    val data = dataSnapshot.getValue(Route::class.java)
                    data?.let { routeList.add(it) }
                }
                onDataChanged(routeList)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("FirebaseRepository", "Error al leer datos")
            }
        })
    }
}