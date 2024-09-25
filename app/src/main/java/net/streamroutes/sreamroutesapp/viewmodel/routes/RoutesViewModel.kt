package net.streamroutes.sreamroutesapp.viewmodel.routes

import android.media.Image
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import net.streamroutes.sreamroutesapp.data.model.routeModel.Route
import net.streamroutes.sreamroutesapp.data.repository.FirebaseRepository
import net.streamroutes.sreamroutesapp.data.repository.RemoteRepository

class RoutesViewModel(private val repository: FirebaseRepository) : ViewModel() {
    private val _routeList = MutableLiveData<List<Route>>()
    val routeList: LiveData<List<Route>> = _routeList

    fun insertLocation(ruta: Route){
        repository.insertLocation(ruta,
            onSuccess = {

            },
            onFailure = {

            }
        )
    }

    fun getLocation(){
        repository.getLocation { rutas ->
            _routeList.value = rutas
        }
    }

    fun getStaticRoutes() {
        val routeList = mutableListOf<Route>()
        val db = Firebase.firestore

        db.collection("routes")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val dato = document.toObject(Route::class.java)
                    routeList.add(dato)
                    //Log.d("VIEWMODELRUTAS", dato.toString())
                }

                // Actualiza _routeList solo después de que los datos se hayan recuperado
                Log.d("VIEWMODELRUTAS", routeList.toString())
                _routeList.value = routeList
            }
            .addOnFailureListener { exception ->
                Log.d("VIEWMODELRUTAS", "Error getting documents: ", exception)
            }
    }
}

class RoutesViewModelFactory(
    private val firebaseRepository: FirebaseRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(RoutesViewModel::class.java)){
            return RoutesViewModel(firebaseRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
/*
data class Route(
    val name : String,
    val time : String,
    val stops : List<Pair<String, LatLng>>,
    val realTime : Boolean,
    val imgs : List<Image>
)

data class RouteUiState(
    val routes : List<Route>,
    val filter : String,
    val currentLocation : LatLng,
)*/