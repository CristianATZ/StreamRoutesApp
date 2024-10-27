package net.streamroutes.sreamroutesapp.viewmodel.routes

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import net.streamroutes.sreamroutesapp.core.data.repository.RouteRepository
import net.streamroutes.sreamroutesapp.core.domain.model.Route
import net.streamroutes.sreamroutesapp.data.repository.FirebaseRepository

class RoutesViewModel(private val repository: RouteRepository) : ViewModel() {
    private val _routes = MutableStateFlow<List<Route>>(emptyList())
    val routes: StateFlow<List<Route>> get() = _routes

    init {
        viewModelScope.launch {
            repository.getAllRoutes().collect { routeList ->
                _routes.value = routeList
            }
        }
    }
}

class RoutesViewModelFactory(
    private val routesRepository: RouteRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(RoutesViewModel::class.java)){
            return RoutesViewModel(routesRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
