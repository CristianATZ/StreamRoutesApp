package net.streamroutes.sreamroutesapp.Screens.MenuScreens

import androidx.lifecycle.ViewModel

class ChatViewModel() : ViewModel() {

}

data class ChatState(
    val Messages : List<String>
)
