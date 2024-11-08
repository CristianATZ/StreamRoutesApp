package net.streamroutes.sreamroutesapp.features.authentication.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.compose.primary

@Composable
fun DisplayText(
    modifier: Modifier = Modifier,
    text: String = ""
) {
    Text(
        text = text,
        style = typography.displayLarge,
        color = primary,
        modifier = modifier.padding(horizontal = 16.dp).fillMaxWidth()
    )
}