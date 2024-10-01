package net.streamroutes.sreamroutesapp.features.authentication.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.primary

@Preview
@Composable
fun PrimaryOutlinedButton(
    modifier: Modifier = Modifier,
    text: String = "",
    onClick: () -> Unit = {}
) {
    OutlinedButton(
        onClick = onClick,
        shape = shapes.small,
        border = BorderStroke(1.dp, primary),
        modifier = modifier
            .fillMaxWidth(0.9f)
    ) {
        Text(
            text = text,
            color = primary,
            fontWeight = FontWeight.Bold
        )
    }
}