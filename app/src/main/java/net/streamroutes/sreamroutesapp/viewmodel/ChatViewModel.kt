package net.streamroutes.sreamroutesapp.viewmodel

import androidx.lifecycle.ViewModel

class ChatViewModel() : ViewModel() {

}

data class ChatUiState(
    val Messages : List<String>
)
