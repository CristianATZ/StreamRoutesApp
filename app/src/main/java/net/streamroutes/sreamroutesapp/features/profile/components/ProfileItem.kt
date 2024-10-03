package net.streamroutes.sreamroutesapp.features.profile.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.RumappAppTheme

@Preview(showBackground = true)
@Composable
fun ProfileItem(
    modifier: Modifier = Modifier,
    title: String = "Title",
    description: String = "Description",
    icon: ImageVector = Icons.Outlined.Person,
    iconDescription: String = "Icon Description"
) {
    Row(
        modifier = modifier
            .fillMaxWidth(0.8f)
            .padding(bottom = 32.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = iconDescription,
            modifier = Modifier.graphicsLayer(alpha = 0.5f)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = title,
                style = typography.titleMedium,
                modifier = Modifier.graphicsLayer(alpha = 0.5f)
            )

            Text(
                text = description,
                style = typography.bodyMedium,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth().heightIn(max = 100.dp)
            )
        }
    }
}