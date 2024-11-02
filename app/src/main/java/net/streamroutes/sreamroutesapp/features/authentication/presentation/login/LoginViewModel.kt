package net.streamroutes.sreamroutesapp.features.authentication.presentation.login

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import net.streamroutes.sreamroutesapp.core.data.repository.RouteRepository
import net.streamroutes.sreamroutesapp.core.data.repository.UserRepository
import net.streamroutes.sreamroutesapp.core.domain.model.User
import net.streamroutes.sreamroutesapp.viewmodel.routes.RoutesViewModel

class LoginViewModel(private val userRepository: UserRepository) : ViewModel() {
    // Variable usada para guardar el resultado del inicio de sesión
    private val _loginResult = MutableStateFlow<Boolean?>(null)
    val loginResult: StateFlow<Boolean?> = _loginResult

    // Variable usada para obtener el usuari actual (como modelo FirebaseUser)
    private val _currentUser = MutableStateFlow<FirebaseUser?>(null)
    val currentUser: StateFlow<FirebaseUser?> = _currentUser

    // Variable usada para obtener el usuari actual (como modelo User)
    private val _userData = MutableStateFlow<User?>(null)
    val userData: StateFlow<User?> = _userData


    /**
     * Inicializador del viewModel para obtener el usuario actual logueado si existe
     */
    init {
        val currentUser = userRepository.getCurrentUser()
        if(currentUser != null) {
            loadUserData(currentUser.uid)
        }
    }


    /**
     * Método usado para iniciar sesión
     */
    fun loginUser(user: User) {
        viewModelScope.launch {
            _loginResult.value = null
            val result = userRepository.loginUser(user)
            if(result.isSuccess){
                _loginResult.value = true
                _currentUser.value = result.getOrNull()
            } else {
                _loginResult.value = false
                _currentUser.value = null
            }
        }
    }


    /**
     * Método usado para cerrar sesión
     */
    fun signOut() {
        userRepository.signOut()
    }


    /**
     * Método privado usado para cargar la información del usuario
     */
    private fun loadUserData(uid: String){
        viewModelScope.launch {
            val result = userRepository.getUserData(uid)
            if(result.isSuccess){
                _userData.value = result.getOrNull()
            } else {
                _userData.value = null
            }
        }
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
