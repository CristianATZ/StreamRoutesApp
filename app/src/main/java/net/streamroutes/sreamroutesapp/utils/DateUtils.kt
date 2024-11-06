package net.streamroutes.sreamroutesapp.utils

import java.security.MessageDigest
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Date
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

    /**
     * Método usado para dar formato a un número telefónico
     */
    fun formatPhoneNumber(phone: String?): String {
        if(phone.equals("")) return "Sin número de teléfono registrado aún"
        if (phone.isNullOrEmpty()) return "cargando..."
        return "(+52) ${phone.substring(0, 3)} ${phone.substring(3, 6)} ${phone.substring(6, 10)}"
    }

    /**
     * Método usado para dar formato al nombre de un usuario
     */
    fun formatName(name: String?, lastName: String?, lastName2: String?): String {
        if(name.equals("") or lastName.equals("") or lastName2.equals("")) return "Sin nombre registrado aún"
        if(name.isNullOrEmpty() or lastName.isNullOrEmpty() or lastName2.isNullOrEmpty()) return "cargando..."
        return "${name} ${lastName} ${lastName2}"
    }

    /**
     * Método usado para dar formato a una fecha
     */
    fun formatDate(dateString: String?): String {
        if(dateString.isNullOrEmpty()) return "cargando..."
        val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val outputFormat = SimpleDateFormat("d 'de' MMMM 'del' yyyy", Locale("es", "ES"))

        val date = inputFormat.parse(dateString)
        return outputFormat.format(date!!)
    }

    /**
     * Método usado para obtener la fecha actual en formato "yyyy-MM-dd HH:mm:ss"
     */
    fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        return dateFormat.format(Date())
    }


    /**
     * Método usado para cifrar la contraseña a la hora de registrar un usuario
     */
    fun hashPassword(password: String): String {
        val md = MessageDigest.getInstance("SHA-256")
        val hashedBytes = md.digest(password.toByteArray())
        return hashedBytes.joinToString("") { "%02x".format(it) }
    }
}