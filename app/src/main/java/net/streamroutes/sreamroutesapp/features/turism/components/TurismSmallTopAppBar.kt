package net.streamroutes.sreamroutesapp.features.turism.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.features.components.CustomTopAppBar
import net.streamroutes.sreamroutesapp.features.components.NavigationButton

@Composable
fun TurismSmallTopAppBar(
    modifier: Modifier = Modifier,
    title: String = "",
    onBackPressed: () -> Unit = {}
) {
    Column {
        CustomTopAppBar(
            title = { 
                Text(text = title)
            },
            navigationIcon = {
                NavigationButton(
                    icon = Icons.Outlined.ArrowBackIosNew,
                    iconDescription = stringResource(id = R.string.iconBackTurism),
                    onButtonPressed = onBackPressed
                )
            }
        )

        HorizontalDivider()
    }
}