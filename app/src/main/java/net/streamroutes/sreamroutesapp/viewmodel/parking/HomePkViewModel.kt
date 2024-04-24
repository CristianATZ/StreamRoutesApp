package net.streamroutes.sreamroutesapp.viewmodel.parking

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomePkViewModel(): ViewModel() {
    private val _uiState = MutableStateFlow(HomePkUiState())
    val uiState: StateFlow<HomePkUiState> = _uiState.asStateFlow()

    var selection by mutableIntStateOf(0)
        private set

    var verTodo by mutableStateOf(false)
        private set

    var iniciarRecorrido by mutableStateOf(false)
        private set

    init {
        _uiState.value = HomePkUiState(
            selection,
            verTodo,
            iniciarRecorrido
        )
    }

    fun updateSelection(_selection: Int){
        selection = _selection

    }

    fun updateVerTodo(_verTodo: Boolean){
        verTodo = _verTodo
    }

    fun updateIniciarRecorrido(_iniciarRecorrido: Boolean){
        iniciarRecorrido = _iniciarRecorrido
    }
}

data class HomePkUiState(
    val selection: Int = 0,
    val verTodo: Boolean = false,
    val iniciarRecorrido: Boolean = false
)