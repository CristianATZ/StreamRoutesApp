package net.streamroutes.sreamroutesapp.Screens.Start

import androidx.lifecycle.ViewModel

class LoginViewModel() : ViewModel() {

}

data class LoginState(
    val phone : String,
    val pass : String
)