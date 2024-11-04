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
    fun updateUserData(updUser: User) {
        viewModelScope.launch {
            _updateResult.value = null
            val currentUser = userRepository.getCurrentUser()
            if (currentUser != null) {
                val updateFields = createUpdateMap(updUser)
                val result = userRepository.updateUserData(currentUser.uid, updateFields)
                _updateResult.value = result.isSuccess
                if (result.isSuccess) {
                    // Volver a actualizar la variable userData
                    _userData.value = _userData.value?.copy(
                        names = updUser.names.ifEmpty { _userData.value?.names ?: "" },
                        username = updUser.username.ifEmpty { _userData.value?.username ?: "" },
                        lastName1 = updUser.lastName1.ifEmpty { _userData.value?.lastName1 ?: "" },
                        lastName2 = updUser.lastName2.ifEmpty { _userData.value?.lastName2 ?: "" },
                        email = updUser.email.ifEmpty { _userData.value?.email ?: "" },
                        phoneNumber = updUser.phoneNumber.ifEmpty { _userData.value?.phoneNumber ?: "" },
                        password = updUser.password.ifEmpty { _userData.value?.password ?: "" },
                        description = updUser.description.ifEmpty { _userData.value?.description ?: "" },
                        gender = updUser.gender.ifEmpty { _userData.value?.gender ?: "" },
                        address = updUser.address.ifEmpty { _userData.value?.address ?: "" },
                        neighborhood = updUser.neighborhood.ifEmpty { _userData.value?.neighborhood ?: "" },
                        numberAddress = updUser.numberAddress.ifEmpty { _userData.value?.numberAddress ?: "" },
                        country = updUser.country.ifEmpty { _userData.value?.country ?: "" },
                        state = updUser.state.ifEmpty { _userData.value?.state ?: "" },
                        birthday = updUser.birthday.ifEmpty { _userData.value?.birthday ?: "" },
                        createdAt = updUser.createdAt.ifEmpty { _userData.value?.createdAt ?: "" },
                    )
                }
            } else {
                _updateResult.value = false
            }
        }
    }


    // Método que crea el map con los campos a actualizar
    private fun createUpdateMap(updUser: User): Map<String, Any> {
        val updateMap = mutableMapOf<String, Any>()
        _userData.value?.let { currentUserData ->
            // Solo agregar al mapa si el campo no está vacio en updUser
            updUser.names.takeIf { it.isNotEmpty() }?.let { updateMap["names"] = it }
            updUser.username.takeIf { it.isNotEmpty() }?.let { updateMap["username"] = it }
            updUser.lastName1.takeIf { it.isNotEmpty() }?.let { updateMap["lastName1"] = it }
            updUser.lastName2.takeIf { it.isNotEmpty() }?.let { updateMap["lastName2"] = it }
            updUser.email.takeIf { it.isNotEmpty() }?.let { updateMap["email"] = it }
            updUser.password.takeIf { it.isNotEmpty() }?.let { updateMap["password"] = it }
            updUser.phoneNumber.takeIf { it.isNotEmpty() }?.let { updateMap["phoneNumber"] = it }
            updUser.description.takeIf { it.isNotEmpty() }?.let { updateMap["description"] = it }
            updUser.gender.takeIf { it.isNotEmpty() }?.let { updateMap["gender"] = it }
            updUser.address.takeIf { it.isNotEmpty() }?.let { updateMap["address"] = it }
            updUser.neighborhood.takeIf { it.isNotEmpty() }?.let { updateMap["neighborhood"] = it }
            updUser.numberAddress.takeIf { it.isNotEmpty() }?.let { updateMap["numberAddress"] = it }
            updUser.country.takeIf { it.isNotEmpty() }?.let { updateMap["country"] = it }
            updUser.state.takeIf { it.isNotEmpty() }?.let { updateMap["state"] = it }
            updUser.birthday.takeIf { it.isNotEmpty() }?.let { updateMap["birthday"] = it }
        }
        return updateMap
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