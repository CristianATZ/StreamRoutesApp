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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.features.authentication.presentation.login.LoginViewModel
import net.streamroutes.sreamroutesapp.features.profile.components.ProfileItem
import net.streamroutes.sreamroutesapp.features.profile.components.ProfileTopBar


@Composable
fun ProfileScreen(
    loginViewModel: LoginViewModel,
    modifier: Modifier = Modifier
) {
    val userData by loginViewModel.userData.collectAsState()

    // no pasar el modifier, solo en caso de que no se coloree
    // si no se colorea, usar scaffold para encapsular las cosas
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        ProfileTopBar(user = formatName(userData?.names, userData?.lastName1, userData?.lastName2))

        Spacer(modifier = Modifier.size(32.dp))

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
            description = userData?.description ?: "cargando...",
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
            onClick = { /*TODO*/ },
            shape = shapes.small,
            modifier = Modifier
                .padding(vertical = 16.dp)
                .fillMaxWidth(0.9f)
                .align(Alignment.CenterHorizontally)
        ) {
            Text(text = stringResource(id = R.string.btnEditProfile))
        }
    }
}


fun formatPhoneNumber(phone: String?): String {
    if (phone.isNullOrEmpty()) return "cargando..."
    return "(+52) ${phone.substring(0, 3)} ${phone.substring(3, 6)} ${phone.substring(6, 10)}"
}

fun formatName(name: String?, lastName: String?, lastName2: String?): String {
    if(name.isNullOrEmpty() or lastName.isNullOrEmpty() or lastName2.isNullOrEmpty()) return "cargando..."
    return "${name} ${lastName} ${lastName2}"
}