package net.streamroutes.sreamroutesapp.features.profile.presentation.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.core.navigation.ProfileNavigation
import net.streamroutes.sreamroutesapp.features.authentication.presentation.login.LoginViewModel
import net.streamroutes.sreamroutesapp.features.profile.components.ProfileItem
import net.streamroutes.sreamroutesapp.features.profile.components.ProfileTopBar
import net.streamroutes.sreamroutesapp.utils.DateUtils.formatDate
import net.streamroutes.sreamroutesapp.utils.DateUtils.formatName
import net.streamroutes.sreamroutesapp.utils.DateUtils.formatPhoneNumber

@Composable
fun EditProfileMain(
    profileViewModel: ProfileViewModel = hiltViewModel(),
    onBackPressed: () -> Unit
) {
    val profileNavHostController = rememberNavController()
    ProfileNavigation(
        navHostController = profileNavHostController,
        onBackPressed = onBackPressed,
        profileViewModel = profileViewModel
    )
}

@Composable
fun ProfileScreen(
    profileViewModel: ProfileViewModel = hiltViewModel(),
    onBackPressed: () -> Unit,
    onEditProfile: () -> Unit,
) {
    val userData by profileViewModel.userData.collectAsState()

    // no pasar el modifier, solo en caso de que no se coloree
    // si no se colorea, usar scaffold para encapsular las cosas

    Scaffold(
        topBar = {
            ProfileTopBar(
                user = formatName(userData?.names, userData?.lastName1, userData?.lastName2),
                date = formatDate(userData?.createdAt),
                onBackPressed = onBackPressed
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Spacer(Modifier.size(16.dp))

            ProfileItem(
                title = stringResource(id = R.string.lblUser),
                description = userData?.username ?: "cargando...",
                icon = Icons.Outlined.Person,
                iconDescription = stringResource(id = R.string.iconUser),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )

            ProfileItem(
                title = stringResource(id = R.string.lblDescription),
                description =
                if (userData?.description.equals("")) "Sin descripción agregada aún"
                else userData?.description ?: "cargando..."
                ,
                icon = Icons.Outlined.Info,
                iconDescription = stringResource(id = R.string.iconInformation),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )

            ProfileItem(
                title = stringResource(id = R.string.lblEmail),
                description = userData?.email ?: "cargando...",
                icon = Icons.Outlined.Email,
                iconDescription = stringResource(id = R.string.iconEmail),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )

            ProfileItem(
                title = stringResource(id = R.string.lblPhone),
                description = formatPhoneNumber(userData?.phoneNumber),
                icon = Icons.Outlined.Phone,
                iconDescription = stringResource(id = R.string.iconPhone),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = onEditProfile,
                shape = shapes.small,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            ) {
                Text(text = stringResource(id = R.string.btnEditProfile))
            }

            Spacer(modifier = Modifier.size(16.dp))
        }
    }
}