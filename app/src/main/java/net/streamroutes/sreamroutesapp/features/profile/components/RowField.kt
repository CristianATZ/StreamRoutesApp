package net.streamroutes.sreamroutesapp.features.profile.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun RowField(
    modifier: Modifier = Modifier,
    title: String = "Title",
    description: String = "Description",
    onClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .clickable {
                onClick()
            }
    ) {

        Spacer(modifier = Modifier.size(16.dp))

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.size(16.dp))

            Text(
                text = title,
                style = typography.bodyLarge
            )

            Text(
                text = description,
                style = typography.bodySmall,
                modifier = Modifier.graphicsLayer(alpha = 0.5f)
            )

            Spacer(modifier = Modifier.size(16.dp))
        }
    }
}