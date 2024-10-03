package net.streamroutes.sreamroutesapp.features.profile.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.features.components.NavigationButton

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun ProfileTopBar(
    modifier: Modifier = Modifier,
    user: String = "Usuario Usuario Usuario Usuario",
    date: String = "Jul. 2024",
    onBackPressed: () -> Unit = {}
) {
    Column {
        LargeTopAppBar(
            title = {
                Column {
                    Text(
                        text = user,
                        style = typography.headlineSmall
                    )

                    Text(
                        text = "Activo desde - $date",
                        style = typography.bodyMedium,
                        modifier = Modifier.graphicsLayer(alpha = 0.5f)
                    )
                }
            },
            navigationIcon = {
                NavigationButton(
                    icon = Icons.Outlined.ArrowBackIosNew,
                    iconDescription = stringResource(id = R.string.iconBackProfile),
                    onButtonPressed = onBackPressed
                )
            }
        )

        HorizontalDivider()
    }
}