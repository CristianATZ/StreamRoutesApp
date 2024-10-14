package net.streamroutes.sreamroutesapp.features.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import net.streamroutes.sreamroutesapp.R

@Composable
fun MapAllOptions(
    onClick: () -> Unit = {}
) {
    Box {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .height(100.dp)
                .background(colorScheme.surfaceContainerHighest, shapes.large)
        ) {

        }

        Button(
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = colorScheme.surface,
                contentColor = colorScheme.onSurface
            ),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 4.dp
            ),
            shape = shapes.large,
            modifier = Modifier.align(Alignment.Center)
        ) {
            Text(text = stringResource(id = R.string.lblViewMap))
        }
    }
}