package net.streamroutes.sreamroutesapp.viewmodel.routes

import androidx.lifecycle.ViewModel

class ChatViewModel() : ViewModel() {

}

data class ChatUiState(
    val Messages : List<String>
)
