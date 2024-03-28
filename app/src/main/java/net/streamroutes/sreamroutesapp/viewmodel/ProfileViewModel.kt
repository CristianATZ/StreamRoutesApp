package net.streamroutes.sreamroutesapp.viewmodel

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

@RequiresApi(Build.VERSION_CODES.O)
class ProfileViewModel(): ViewModel() {
    private val _uiState = MutableStateFlow(
        ProfileUiState(
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
        )
    )
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    var fav by mutableStateOf(0)
        private set

    var payment by mutableStateOf(SuscriptionType.Ninguna)
        private set

    var verification by mutableStateOf(false)
        private set

    var intereses by mutableStateOf(listOf(""))
        private set

    var name by mutableStateOf("")
        private set

    var lastName by mutableStateOf("")
        private set

    var email by mutableStateOf("")
        private set

    var country by mutableStateOf("")
        private set

    var state by mutableStateOf("")
        private set

    var address by mutableStateOf("")
        private set

    var number by mutableStateOf("")
        private set

    var suburb by mutableStateOf("")
        private set

    var postal by mutableStateOf("")
        private set

    var birthday by mutableStateOf(LocalDate.now())
        private set

    var gender by mutableStateOf(Gender.Masculino)
        private set

    var ocupation by mutableStateOf("")
        private set

    var id by mutableStateOf("")
        private set

    var user by mutableStateOf("")
        private set

    var start by mutableStateOf(LocalDate.now())
        private set

    var end by mutableStateOf(LocalDate.now())
        private set

    var type by mutableStateOf(SuscriptionType.Ninguna)
        private set

    var badge by mutableStateOf("")
        private set

    var phone by mutableStateOf("")
        private set

    var pass by mutableStateOf("")
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