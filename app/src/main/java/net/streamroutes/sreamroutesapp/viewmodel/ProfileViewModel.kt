package net.streamroutes.sreamroutesapp.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
class ProfileViewModel () : ViewModel() {

}

enum class SuscriptionType {
    Estudiante, General, Turista, Anual, Ninguna
}

enum class Gender {
    Masculino, Femenino, NoDecir
}

@RequiresApi(Build.VERSION_CODES.O)
data class ProfileState(
    // perfil
    val fav : Int = 0,
    val payment : SuscriptionType = SuscriptionType.Ninguna,
    val verification : Boolean = false,
    val intereses : List<String> = listOf(
        "Entretenemiento", "Comida", "Ropa"
    ),

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