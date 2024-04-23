package net.streamroutes.sreamroutesapp.viewmodel.routes

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ChangeViewModel: ViewModel() {
    // Change UI State
    private val _uiState = MutableStateFlow(ChangeUiState())
    val uiState: StateFlow<ChangeUiState> = _uiState.asStateFlow()

    var password by mutableStateOf("")
        private set

    var passwordVisibility by mutableStateOf(true)
        private set

    var confirmPass by mutableStateOf("")
        private set

    var confirmPassVisibility by mutableStateOf(true)
        private set

    init {
        _uiState.value = ChangeUiState(
            password = password,
            passwordVisibility = passwordVisibility,
            confirmPass = confirmPass,
            confirmPassVisibility = confirmPassVisibility
        )
    }

    fun updatePassword(_password: String){
        password = _password
    }

    fun updatePasswordVisibility(_flag: Boolean){
        passwordVisibility = _flag
    }

    fun updateConfirmPass(_password: String){
        confirmPass = _password
    }

    fun updateConfirmPassVisibility(_flag: Boolean){
        confirmPassVisibility = _flag
    }
}

data class ChangeUiState(
    val password: String = "",
    val passwordVisibility: Boolean = true,
    val confirmPass: String = "",
    val confirmPassVisibility: Boolean = true
)