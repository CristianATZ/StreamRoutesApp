package net.streamroutes.sreamroutesapp.features.authentication.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose.primary

@Preview
@Composable
fun PrimaryFilledButton(
    modifier: Modifier = Modifier,
    text: String = "",
    onClick: () -> Unit = {}
) {
    Button(
        onClick = onClick,
        shape = shapes.small,
        colors = ButtonDefaults.buttonColors(
            containerColor = primary,
            contentColor = Color.White
        ),
        modifier = modifier
            .fillMaxWidth(0.9f)
    ) {
        Text(
            text = text,
            fontWeight = FontWeight.Bold
        )
    }
}