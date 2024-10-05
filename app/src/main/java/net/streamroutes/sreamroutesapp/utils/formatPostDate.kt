package net.streamroutes.sreamroutesapp.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import net.streamroutes.sreamroutesapp.R
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Locale

// obtener fecha para la publicacion
@Composable
fun formatPostDateTime(postDateTime: LocalDateTime): String {
    val now = LocalDateTime.now()

    // Calcular la diferencia entre ahora y la fecha de publicación
    val minutesDifference = ChronoUnit.MINUTES.between(postDateTime, now)
    val hoursDifference = ChronoUnit.HOURS.between(postDateTime, now)
    val daysDifference = ChronoUnit.DAYS.between(postDateTime, now)

    // Menos de un minuto
    if (minutesDifference < 1) {
        return stringResource(id = R.string.post_few_seconds_ago)
    }

    // Menos de una hora
    if (hoursDifference < 1) {
        return if (minutesDifference == 1L) {
            stringResource(id = R.string.post_minutes_ago, minutesDifference)
        } else {
            stringResource(id = R.string.post_minutes_ago_plural, minutesDifference)
        }
    }

    // Menos de 24 horas
    if (daysDifference < 1) {
        return if (hoursDifference == 1L) {
            stringResource(id = R.string.post_hours_ago, hoursDifference)
        } else {
            stringResource(id = R.string.post_hours_ago_plural, hoursDifference)
        }
    }

    // Entre 1 y 6 días
    if (daysDifference in 1..6) {
        return if (daysDifference == 1L) {
            stringResource(id = R.string.post_days_ago, daysDifference)
        } else {
            stringResource(id = R.string.post_days_ago_plural, daysDifference)
        }
    }

    // Más de 6 días - mostrar la fecha con el formato corto (ej: "10 dic 2023")
    val formatter = DateTimeFormatter.ofPattern("d MMM yyyy", Locale("es"))
    return postDateTime.format(formatter)
}