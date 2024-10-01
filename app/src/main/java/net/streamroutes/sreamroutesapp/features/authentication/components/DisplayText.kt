package net.streamroutes.sreamroutesapp.features.authentication.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.compose.primary

@Composable
fun DisplayText(
    modifier: Modifier = Modifier,
    text: String = ""
) {
    Text(
        text = text,
        style = typography.displayLarge,
        //fontWeight = FontWeight.Bold,
        color = primary,
        modifier = modifier.fillMaxWidth(0.9f)
    )
}