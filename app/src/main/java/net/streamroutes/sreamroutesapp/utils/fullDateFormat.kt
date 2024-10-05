package net.streamroutes.sreamroutesapp.utils

import androidx.compose.runtime.Composable
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun fullDateFormat(postDateTime: LocalDateTime): String {
    val formatter = DateTimeFormatter.ofPattern("d MMM yyyy", Locale("es"))
    return postDateTime.format(formatter)
}