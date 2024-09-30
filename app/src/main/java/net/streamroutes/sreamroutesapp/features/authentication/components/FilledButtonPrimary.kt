package net.streamroutes.sreamroutesapp.features.authentication.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun FilledButtonPrimary(
    modifier: Modifier = Modifier,
    text: String = ""
) {
    Button(
        onClick = { /*TODO*/ },
        shape = shapes.small,
        modifier = modifier
            .fillMaxWidth(0.8f)
    ) {
        Text(text = text)
    }
}