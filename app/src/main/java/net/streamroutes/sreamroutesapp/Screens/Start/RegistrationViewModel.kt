package net.streamroutes.sreamroutesapp.Screens.Start

import androidx.lifecycle.ViewModel

class RegistrationViewModel() : ViewModel() {

}

data class RegistrationState(
    val phone : String,
    val pass : String,
    val repass : String
)