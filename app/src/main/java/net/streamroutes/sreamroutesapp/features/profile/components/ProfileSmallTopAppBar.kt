package net.streamroutes.sreamroutesapp.features.profile.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.features.components.CustomTopAppBar
import net.streamroutes.sreamroutesapp.features.components.NavigationButton

@Preview
@Composable
fun ProfileSmallTopAppBar(
    modifier: Modifier = Modifier,
    title: String = "Title",
    onBackPressed: () -> Unit = {}
) {
    Column {
        CustomTopAppBar(
            title = {
                Text(
                    text = title
                )
            },
            navigationIcon = {
                NavigationButton(
                    icon = Icons.Outlined.ArrowBackIosNew,
                    iconDescription = stringResource(id = R.string.iconBackEditProfile),
                    onButtonPressed = onBackPressed
                )
            },
            modifier = modifier
        )

        HorizontalDivider()
    }
}