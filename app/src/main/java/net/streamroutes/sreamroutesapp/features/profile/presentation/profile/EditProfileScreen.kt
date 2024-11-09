package net.streamroutes.sreamroutesapp.features.profile.presentation.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.features.profile.components.ProfileSmallTopAppBar
import net.streamroutes.sreamroutesapp.features.profile.components.RowField

@Composable
fun EditProfileScreen(
    onEditAccount: () -> Unit,
    onEditPersonalInformation: () -> Unit,
    onPosts: () -> Unit,
    onRoutes: () -> Unit,
    onHistory: () -> Unit,
    onBackPressed: () -> Unit
) {
    // no pasar el modifier, solo en caso de que no se coloree
    // si no se colorea, usar scaffold para encapsular las cosas

    Scaffold(
        topBar =  {
            ProfileSmallTopAppBar(
                title = stringResource(id = R.string.lblEditProfile),
                onBackPressed = onBackPressed
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            // editar cuenta
            RowField(
                title = stringResource(id = R.string.lblEditAccount),
                description = stringResource(id = R.string.lblEditAccountDescription),
                onClick = onEditAccount,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
            )

            // editar informacion personal
            RowField(
                title = stringResource(id = R.string.lblEditPersonalInformation),
                description = stringResource(id = R.string.lblEditPersonalInformationDescription),
                onClick = onEditPersonalInformation,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
            )

            // publicaciones
            RowField(
                title = stringResource(id = R.string.lblPosts),
                description = stringResource(id = R.string.lblPostsDescription),
                onClick = onPosts,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
            )

            // rutas
            RowField(
                title = stringResource(id = R.string.lblRoutes),
                description = stringResource(id = R.string.lblRoutesDescription),
                onClick = onRoutes,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
            )

            // historial
            RowField(
                title = stringResource(id = R.string.lblHistory),
                description = stringResource(id = R.string.lblHistoryDescription),
                onClick = onHistory,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
            )
        }
    }
}