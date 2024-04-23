package net.streamroutes.sreamroutesapp.viewmodel.routes

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalDate

/*
fav = 0,
payment = SuscriptionType.Estudiante,
verification = false,
intereses = listOf(
    "Entretenemiento", "Comida", "Ropa"
),
name = "Cristian Alexis",
lastName = "Torres Zavala",
email = "cris@gmail.com",
country = "México",
state = "Guanajuato",
number = "132",
suburb = "niideaalv",
address = "",
postal = "",
birthday = LocalDate.now(),
gender = Gender.Masculino,
ocupation = "Estudiante y Cinepolito",
id = "cy",
user = "criseschido12345",
start = LocalDate.now(),
end = LocalDate.now(),
type = SuscriptionType.Estudiante,
badge = "cy",
phone = "445 141 1834",
pass = "lavidaesroja"
 */

@RequiresApi(Build.VERSION_CODES.O)
class ProfileViewModel(): ViewModel() {
    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    var fav by mutableStateOf(4)
        private set

    var payment by mutableStateOf(SuscriptionType.Estudiante)
        private set

    var verification by mutableStateOf(true)
        private set

    var intereses by mutableStateOf(listOf("Entretenimiento", "Comida", "Ropa"))
        private set

    var name by mutableStateOf("Cristian Alexis")
        private set

    var lastName by mutableStateOf("Torres Zavala")
        private set

    var email by mutableStateOf("esteroidito@gmail.com")
        private set

    var country by mutableStateOf("Los Mexicos")
        private set

    var state by mutableStateOf("casado xd")
        private set

    var address by mutableStateOf("Moroleon, Gto.")
        private set

    var number by mutableStateOf("234")
        private set

    var suburb by mutableStateOf("sepalabola")
        private set

    var postal by mutableStateOf("58533")
        private set

    var birthday by mutableStateOf(LocalDate.now())
        private set

    var gender by mutableStateOf(Gender.Masculino)
        private set

    var ocupation by mutableStateOf("Estudiante y cinepolito")
        private set

    var id by mutableStateOf("cy")
        private set

    var user by mutableStateOf("crsieschido123")
        private set

    var start by mutableStateOf(LocalDate.now())
        private set

    var end by mutableStateOf(LocalDate.now())
        private set

    var type by mutableStateOf(SuscriptionType.Estudiante)
        private set

    var badge by mutableStateOf("Mensual")
        private set

    var phone by mutableStateOf("445 141 1834")
        private set

    var pass by mutableStateOf("Vigente")
        private set

    init {
        _uiState.value = ProfileUiState(
            fav,
            payment,
            verification,
            intereses,
            name,
            lastName,
            email,
            country,
            state,
            address,
            number,
            suburb,
            postal,
            birthday,
            gender,
            ocupation,
            id,
            user,
            start,
            end,
            type,
            badge,
            phone,
            pass
        )
    }

    fun updateFav(_fav: Int) {
        fav = _fav
    }

    fun updatePayment(_payment: SuscriptionType) {
        payment = _payment
    }

    fun updateVerification(_verification: Boolean) {
        verification = _verification
    }

    fun updateIntereses(_intereses: List<String>) {
        intereses = _intereses
    }

}

enum class SuscriptionType {
    Estudiante, General, Turista, Anual, Ninguna
}

enum class Gender {
    Masculino, Femenino, NoDecir
}

@RequiresApi(Build.VERSION_CODES.O)
data class ProfileUiState(
    // perfil
    val fav : Int = 0,
    val payment : SuscriptionType = SuscriptionType.Ninguna,
    val verification : Boolean = false,
    val intereses : List<String> = listOf(""),

    // informacion personal
    val name : String = "",
    val lastName : String = "",
    val email : String = "",
    val country : String = "",
    val state : String = "",
    val address : String = "",
    val number : String = "",
    val suburb : String = "",
    val postal : String = "",
    val birthday : LocalDate = LocalDate.now(),
    val gender : Gender = Gender.Masculino,
    val ocupation : String = "",

    // membresia
    val id : String = "",
    val user : String = "",
    val start : LocalDate = LocalDate.now(),
    val end : LocalDate = LocalDate.now(),
    val type : SuscriptionType = SuscriptionType.Ninguna,
    val badge : String = "",

    // seguridad
    val phone : String = "",
    val pass : String = ""
)