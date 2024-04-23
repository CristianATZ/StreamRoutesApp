package net.streamroutes.sreamroutesapp.viewmodel.parking

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ConfigurationPkViewModel(): ViewModel() {
    private val _uiState = MutableStateFlow(ConfigurationPkUiState())
    val uiState: StateFlow<ConfigurationPkUiState> = _uiState.asStateFlow()

    var selection by mutableIntStateOf(0)
        private set

    var verTodo by mutableStateOf(false)
        private set
}

data class ConfigurationPkUiState(
    val selection: Int = 0,
    val verTodo: Boolean = false
)