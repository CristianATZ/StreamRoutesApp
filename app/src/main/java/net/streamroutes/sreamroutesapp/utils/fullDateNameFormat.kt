package net.streamroutes.sreamroutesapp.utils

import androidx.compose.runtime.Composable
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun fullDateNameFormat(postDateTime: LocalDateTime): String {
    val formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale("es"))
    return postDateTime.format(formatter)
}