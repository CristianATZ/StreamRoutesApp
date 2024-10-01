package net.streamroutes.sreamroutesapp.features.authentication.components

import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose.primary

@Preview
@Composable
fun PrimaryTextButton(
    modifier: Modifier = Modifier,
    text: String = "",
) {
    TextButton(
        onClick = { /*TODO*/ },
        shape = shapes.small,
        modifier = modifier
    ) {
        Text(
            text = text,
            color = primary,
            fontWeight = FontWeight.Bold
        )
    }
}
