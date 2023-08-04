package net.streamroutes.sreamroutesapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {

    // variables importantes
    // 0 = ES 1 = EN
    var idioma by mutableStateOf(0)

    // lista de idioma ES
    val es = listOf(
        // main screen
        "Ciudad",
        // menu screen
        "Menu",
        "Nombre Usuario",
        "Tipo Membresia",
        "Ciudad",
        "Version Premium",
        "Rutas",
        "Planifica tu viaje",
        "Compartir Ubicacion",
        "Ubi. act:",
        "No se pudo obtener la ubicacion actual. Asegurate de tener la ubicacion encendida",
        "Comparte",
        "Valoranos",
        "Configuracion",
        "Ayuda y soporte"
    )

    // lista de idioma EN
    val en = listOf(
        // main screen
        "City",
        // menu screen
        "Menu",
        "Username",
        "Membership Type",
        "City",
        "Premium Version",
        "Routes",
        "Plan Your Trip",
        "Share Location",
        "Location: ",
        "Couldn't get current location. Make sure your location is turned on.",
        "Share",
        "Rate Us",
        "Settings",
        "Help and Support"
    )

    fun languageType(): List<String> {
        if (idioma == 0) return es
        return en
    }
}