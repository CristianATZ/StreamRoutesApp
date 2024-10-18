package net.streamroutes.sreamroutesapp.utils

import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Locale

object DateUtils {
    // 00:00:00
    fun formatTime(localTime: LocalTime): String {
        // Crear un formateador para HH:MM:SS
        val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")
        // Devolver el tiempo formateado como cadena
        return localTime.format(formatter)
    }

    // X de ENERO de XXXX
    fun fullDateNameFormat(postDateTime: LocalDateTime): String {
        val formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale("es"))
        return postDateTime.format(formatter)
    }

    // X ene XXXX
    fun fullDateFormat(postDateTime: LocalDateTime): String {
        val formatter = DateTimeFormatter.ofPattern("d MMM yyyy", Locale("es"))
        return postDateTime.format(formatter)
    }
}