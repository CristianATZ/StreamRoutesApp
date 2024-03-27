package net.streamroutes.sreamroutesapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import net.streamroutes.sreamroutesapp.data.AppContainer
import net.streamroutes.sreamroutesapp.data.RemoteContainer

class LoginViewModel: ViewModel() {
    // Login UI State
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    var telefono by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    var passwordVisibility by mutableStateOf(true)
        private set

    init {
        _uiState.value = LoginUiState(
            telefono = telefono,
            password = password,
            passwordVisibility = passwordVisibility
        )
    }

    // Actualizar campos
    fun updateTelefono(_telefono: String){
        telefono = _telefono
    }

    fun updatePassword(_password: String){
        password = _password
    }

    fun updatePasswordVisibility(_flag: Boolean){
        passwordVisibility = _flag
    }
}

data class LoginUiState(
    val telefono: String = "",
    val password: String = "",
    val passwordVisibility: Boolean = true
)