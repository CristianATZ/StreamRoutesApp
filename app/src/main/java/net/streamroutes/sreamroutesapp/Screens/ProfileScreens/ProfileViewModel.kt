package net.streamroutes.sreamroutesapp.Screens.ProfileScreens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@RequiresApi(Build.VERSION_CODES.O)
class ProfileViewModel () : ViewModel() {
    private val _uiState = MutableStateFlow(ProfileState())
    val uiState : StateFlow<ProfileState> = _uiState.asStateFlow()

    init {
        _uiState.value = ProfileState(

        )
    }
}