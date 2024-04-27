package net.streamroutes.sreamroutesapp.viewmodel.routes

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import net.streamroutes.sreamroutesapp.R


class ChatViewModel() : ViewModel() {
    private val _uiState = MutableStateFlow(ChatUiState())
    val uiState: StateFlow<ChatUiState> = _uiState.asStateFlow()

    var messages by mutableStateOf(
        listOf(
            Mensaje(2,"Nayeli", "Hola, ¿alguien puede ayudarme?", "08:20 a.m", R.drawable.usuario_femenino),
            Mensaje(1, "Alan", "Hola amiga, ¿en qué necesitas ayuda?", "08:22 a.m", R.drawable.usuario_masculino),
            Mensaje(1, "Anaid", "Claro que si, ¿qué necesitas?", "08:22 a.m", R.drawable.usuario_femenino),
            Mensaje(2, "Nayeli", "Necesito llegar al callejón del beso pero, no soy de aquí :(", "08:23 a.m", R.drawable.usuario_femenino),
            Mensaje(1, "Alan", "¿Qué ves cerca?", "08:24 a.m", R.drawable.usuario_masculino),
            Mensaje(2, "Nayeli", "Estoy en la Universidad de Guanajuato", "08:25 a.m", R.drawable.usuario_femenino),
            Mensaje(1, "Anaid", "Dirígete una cuadra abajo, para esperar la ruta 5, el autobús tiene franjas azules y tiene en letras grandes RUTA 5", "08:27 a.m", R.drawable.usuario_femenino),
            Mensaje(2, "Nayeli", "Muchas gracias Anaid, también a ti Alan", "08:28 a.m", R.drawable.usuario_femenino)
        )
    )
        private set

    var message by mutableStateOf("")
        private set

    init {
        _uiState.value = ChatUiState(
            messages, message
        )
    }
}

data class ChatUiState(
    val messages : List<Mensaje> = listOf(),
    val message: String = ""
)

data class Mensaje(
    val autor: Int,
    val usuario: String,
    val mensaje: String,
    val hora: String,
    val imagen: Int
)
