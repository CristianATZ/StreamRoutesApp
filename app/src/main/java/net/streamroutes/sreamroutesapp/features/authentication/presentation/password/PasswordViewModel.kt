package net.streamroutes.sreamroutesapp.features.authentication.presentation.password

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import net.streamroutes.sreamroutesapp.core.data.repository.UserRepository
import net.streamroutes.sreamroutesapp.features.profile.presentation.profile.ProfileViewModel

class PasswordViewModel(private val userRepository: UserRepository): ViewModel() {
    // Variable usada para guardar el resultado del cambio de contraseña
    private val _resetPasswordResult  = MutableStateFlow<Boolean?>(null)
    val resetPasswordResult: StateFlow<Boolean?> = _resetPasswordResult

    fun resetPassword(email: String){
        viewModelScope.launch {
            _resetPasswordResult.value = null
            val result = userRepository.resetPassword(email)
            if(result.isSuccess){
                _resetPasswordResult.value = true
            } else {
                _resetPasswordResult.value = false
            }
        }
    }
}

class PasswordViewModelFactory(
    private val userRepository: UserRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(PasswordViewModel::class.java)){
            return PasswordViewModel(userRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}