package net.streamroutes.sreamroutesapp.features.profile.presentation.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.features.profile.components.ProfileSmallTopAppBar

@Preview
@Composable
fun EditPersonlaInfoScreen(
    modifier: Modifier = Modifier
) {
    // no pasar el modifier, solo en caso de que no se coloree
    // si no se colorea, usar scaffold para encapsular las cosas

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        ProfileSmallTopAppBar(
            title = stringResource(id = R.string.lblEditPersonalInformation),
            onBackPressed = {

            }
        )


    }
}