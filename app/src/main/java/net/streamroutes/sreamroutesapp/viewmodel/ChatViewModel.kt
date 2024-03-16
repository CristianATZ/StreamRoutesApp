package net.streamroutes.sreamroutesapp.viewmodel

import androidx.lifecycle.ViewModel

class ChatViewModel() : ViewModel() {

}

data class ChatState(
    val Messages : List<String>
)
