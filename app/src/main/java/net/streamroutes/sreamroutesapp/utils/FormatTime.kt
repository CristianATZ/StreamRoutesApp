package net.streamroutes.sreamroutesapp.utils

import androidx.compose.runtime.Composable
import java.time.LocalTime
import java.time.format.DateTimeFormatter


fun formatTime(localTime: LocalTime): String {
    // Crear un formateador para HH:MM:SS
    val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")
    // Devolver el tiempo formateado como cadena
    return localTime.format(formatter)
}