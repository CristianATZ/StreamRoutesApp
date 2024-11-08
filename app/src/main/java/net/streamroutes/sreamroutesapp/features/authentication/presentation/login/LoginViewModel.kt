package net.streamroutes.sreamroutesapp.features.authentication.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import net.streamroutes.sreamroutesapp.core.data.repository.UserRepository
import net.streamroutes.sreamroutesapp.core.domain.model.User
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    // Variable usada para guardar el resultado del inicio de sesión
    private val _loginResult = MutableStateFlow<Boolean?>(null)
    val loginResult: StateFlow<Boolean?> = _loginResult

    // Variable usada para obtener el usuario actual (como modelo FirebaseUser)
    private val _currentUser = MutableStateFlow<FirebaseUser?>(null)
    val currentUser: StateFlow<FirebaseUser?> = _currentUser

    // Variable usada para obtener el usuari actual (como modelo User)
    private val _userData = MutableStateFlow<User?>(null)
    val userData: StateFlow<User?> = _userData


    /**
     * Método usado para iniciar sesión
     */
    fun loginUser(email: String, password: String) {
        viewModelScope.launch {
            _loginResult.value = null
            val result = userRepository.loginUser(email, password)
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

}