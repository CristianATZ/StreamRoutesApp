package net.streamroutes.sreamroutesapp.features.profile.presentation.profile

import androidx.compose.runtime.currentCompositionErrors
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import net.streamroutes.sreamroutesapp.core.data.repository.UserRepository
import net.streamroutes.sreamroutesapp.core.domain.model.User


class ProfileViewModel(private val userRepository: UserRepository) : ViewModel() {
    // Variable usada para obtener el usuario actual (como modelo FirebaseUser)
    private val _currentUser = MutableStateFlow<FirebaseUser?>(null)
    val currentUser: StateFlow<FirebaseUser?> = _currentUser

    // Variable usada para obtener el usuario actual (como modelo User)
    private val _userData = MutableStateFlow<User?>(null)
    val userData: StateFlow<User?> = _userData

    // Variable usada para guardar el resultado al actualizar un usuario
    private val _updateResult = MutableStateFlow<Boolean?>(null)
    val updateResult: StateFlow<Boolean?> = _updateResult

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


    /**
     * Método usado para editar la información de un usuario
     */
    fun updateUserData(updUser: User){
        viewModelScope.launch {
            _updateResult.value = null
            val currentUser = userRepository.getCurrentUser()
            if(currentUser != null){
                val result = userRepository.updateUserData(currentUser.uid, updUser)
                _updateResult.value = true
                if(result.isSuccess){
                    _userData.value = updUser
                }
            } else {
                _updateResult.value = false
            }
        }
    }
}


class ProfileViewModelFactory(
    private val userRepository: UserRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ProfileViewModel::class.java)){
            return ProfileViewModel(userRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}