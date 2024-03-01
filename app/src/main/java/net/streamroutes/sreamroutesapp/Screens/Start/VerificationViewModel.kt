package net.streamroutes.sreamroutesapp.Screens.Start

import androidx.lifecycle.ViewModel

class VerificationViewModel() : ViewModel() {

}

data class VerificationState(
    val phone : String,
    val code : String
)