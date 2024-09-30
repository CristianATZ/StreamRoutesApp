package net.streamroutes.sreamroutesapp.features.authentication.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun TextButtonPrimary(
    modifier: Modifier = Modifier,
    text: String = ""
) {
    TextButton(
        onClick = { /*TODO*/ },
        shape = shapes.small,
        modifier = modifier
            .fillMaxWidth(0.8f)
    ) {
        Text(text = text)
    }
}