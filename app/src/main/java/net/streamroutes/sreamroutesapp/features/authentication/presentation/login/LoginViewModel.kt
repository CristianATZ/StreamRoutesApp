package net.streamroutes.sreamroutesapp.features.authentication.presentation.login

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import net.streamroutes.sreamroutesapp.core.data.repository.RouteRepository
import net.streamroutes.sreamroutesapp.core.data.repository.UserRepository
import net.streamroutes.sreamroutesapp.core.domain.model.User
import net.streamroutes.sreamroutesapp.viewmodel.routes.RoutesViewModel

class LoginViewModel (private val userRepository: UserRepository): ViewModel() {
    private val _authState = MutableStateFlow<Result<User>?>(null)
    val authState: StateFlow<Result<User>?> = _authState

    /**
     * Método usado para dar de alt a un usuario
     */
    fun signUpUser(user: User){
        viewModelScope.launch {
            val result = userRepository.signUpUser(user)
            _authState.value = result
        }
    }


    /**
     * Método usaro para iniciar sesión
     */
    fun loginUser(user: User){
        viewModelScope.launch {
            val result = userRepository.loginUser(user)
            if(result.isSuccess){
                val user = result.getOrNull()
                user?.let {
                    Log.d("AUTH", "INICIO EXITOSO")
                }
            } else {
                val exception = result.exceptionOrNull()
                exception?.let {
                    Log.d("AUTH", "NO SE PUDO INICIAR SESION VALE. ERROR: ${exception.message}")
                }

            }
        }
    }


    /**
     * Método usado para cerrar sesión
     */
    fun signOut(){
        userRepository.signOut()
    }
}


class LoginViewModelFactory(
    private val userRepository: UserRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(LoginViewModel::class.java)){
            return LoginViewModel(userRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
