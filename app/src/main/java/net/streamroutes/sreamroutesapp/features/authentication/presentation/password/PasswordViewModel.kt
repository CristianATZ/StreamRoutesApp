package net.streamroutes.sreamroutesapp.features.authentication.presentation.password

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import net.streamroutes.sreamroutesapp.core.data.repository.UserRepository
import net.streamroutes.sreamroutesapp.features.profile.presentation.profile.ProfileViewModel
import javax.inject.Inject
import javax.inject.Singleton

@HiltViewModel
class PasswordViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel() {
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