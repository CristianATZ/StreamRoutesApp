package net.streamroutes.sreamroutesapp.features.authentication.presentation.register

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
import net.streamroutes.sreamroutesapp.features.authentication.presentation.login.LoginViewModel
import javax.inject.Inject
import javax.inject.Singleton

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel() {
    private val _authState = MutableStateFlow<Result<User>?>(null)
    val authState: StateFlow<Result<User>?> = _authState

    /**
     * Método usado para dar de alt a un usuario
     */
    fun signUpUser(
        username: String,
        email: String,
        password: String
    ){
        viewModelScope.launch {
            val result = userRepository.signUpUser(username, email, password)
            _authState.value = result
        }
    }

}