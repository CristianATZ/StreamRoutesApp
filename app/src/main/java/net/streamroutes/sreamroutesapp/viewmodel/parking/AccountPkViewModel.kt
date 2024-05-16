package net.streamroutes.sreamroutesapp.viewmodel.parking

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AccountPkViewModel(): ViewModel() {
    private val _uiState = MutableStateFlow(AccountUiState())
    val uiState: StateFlow<AccountUiState> = _uiState.asStateFlow()

    var vehicles by mutableStateOf(
        mutableListOf(
            Vehiculo("PRA46G", TipoVehiculo.CARRO, "Ford", "Ranger", "1991", ColorVehiculo.BLANCO),
            Vehiculo("58ECP3", TipoVehiculo.MOTO, "Italika", "150", "2020", ColorVehiculo.NEGRO),
            Vehiculo("ULK32K", TipoVehiculo.CARRO, "Ford", "Mustang", "1996", ColorVehiculo.ROJO)
        )
    )
        private set

    var selectedVehicle by mutableStateOf(
        vehicles[0]
    )
        private set

    var seeAll by mutableStateOf(false)
        private set

    var user by mutableStateOf(
        Usuario("Cristian Alexis Torres Zavala", "445 141 1834", "esteroidito@gmail.com", "Moroleon, Gto.")
    )
        private set

    init {
        _uiState.value = AccountUiState(
            vehicles,
            selectedVehicle,
            seeAll,
            user
        )
    }

    fun updateVehicles(_list: MutableList<Vehiculo>){
        vehicles = _list
    }

    fun updateSelectedVehicle(_selection: Vehiculo){
        selectedVehicle = _selection
    }

    fun updateSeeAll(_flag: Boolean){
        seeAll = _flag
    }

    fun updateUser(_user: Usuario){
        user = _user
    }
}

data class AccountUiState(
    val vehicles: MutableList<Vehiculo> = mutableListOf(),
    val selectedVehicle: Vehiculo ?= null,
    val seeAll: Boolean = false,
    val user: Usuario ?= null
)

data class Usuario(
    val name: String = "",
    val phone: String = "",
    val email: String = "",
    val location: String = ""
)

enum class ColorVehiculo {
    NINGUNO, BLANCO, NEGRO, GRIS, AZUL, ROJO, VERDE
}

data class Vehiculo(
    val matricula: String,
    val tipo: TipoVehiculo,
    val marca: String,
    val modelo: String,
    val year: String,
    val color: ColorVehiculo
)