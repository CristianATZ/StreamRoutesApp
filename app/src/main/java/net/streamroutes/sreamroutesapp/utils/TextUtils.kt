package net.streamroutes.sreamroutesapp.utils

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import com.example.compose.orange

object TextUtils {
    fun convertTextToOrange(text: String): AnnotatedString {
        // Crear una AnnotatedString
        val annotatedString = buildAnnotatedString {
            // Texto normal (descripción)
            append("$text...") // Puedes cambiar este texto

            // Puntos suspensivos con estilo
            withStyle(style = SpanStyle(color = orange, fontWeight = FontWeight.ExtraBold)) {
                append(" Ver mas")
            }
        }

        return annotatedString
    }
}