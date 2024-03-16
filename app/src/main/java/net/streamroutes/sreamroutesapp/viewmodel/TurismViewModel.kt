package net.streamroutes.sreamroutesapp.viewmodel

import androidx.lifecycle.ViewModel
import net.streamroutes.sreamroutesapp.viewmodel.Route

class TurismViewModel() : ViewModel() {

}

data class TurismState(
    val route : Route
)