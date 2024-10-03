package net.streamroutes.sreamroutesapp.features.profile.presentation.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.features.profile.components.ProfileSmallTopAppBar
import net.streamroutes.sreamroutesapp.features.profile.components.RowField

@Preview(showBackground = true)
@Composable
fun EditProfileScreen(
    modifier: Modifier = Modifier
) {
    // no pasar el modifier, solo en caso de que no se coloree
    // si no se colorea, usar scaffold para encapsular las cosas

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        ProfileSmallTopAppBar(
            title = stringResource(id = R.string.lblEditProfile),
            onBackPressed = { }
        )

        // editar cuenta
        RowField(
            title = stringResource(id = R.string.lblEditAccount),
            description = stringResource(id = R.string.lblEditAccountDescription),
            onClick = {
                // ENVIAR A EDITAR CUENTA
            },
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
        )

        // editar informacion personal
        RowField(
            title = stringResource(id = R.string.lblEditPersonalInformation),
            description = stringResource(id = R.string.lblEditPersonalInformationDescription),
            onClick = {
                // ENVIAR A EDITAR INFORMACION PERSONAL
            },
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
        )

        // publicaciones
        RowField(
            title = stringResource(id = R.string.lblPosts),
            description = stringResource(id = R.string.lblPostsDescription),
            onClick = {
                // ENVIAR A PUBLICACIONES
            },
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
        )

        // rutas
        RowField(
            title = stringResource(id = R.string.lblRoutes),
            description = stringResource(id = R.string.lblRoutesDescription),
            onClick = {
                // ENVIAR A RUTAS
            },
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
        )

        // historial
        RowField(
            title = stringResource(id = R.string.lblHistory),
            description = stringResource(id = R.string.lblHistoryDescription),
            onClick = {
                // ENVIAR A HISTORIAL
            },
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
        )
    }
}