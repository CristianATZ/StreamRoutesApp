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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.features.profile.components.ProfileItem
import net.streamroutes.sreamroutesapp.features.profile.components.ProfileTopBar

@Preview(showBackground = true)
@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        ProfileTopBar()

        Spacer(modifier = Modifier.size(32.dp))

        ProfileItem(
            title = stringResource(id = R.string.lblUser),
            description = "Usuario Usuario",
            icon = Icons.Outlined.Person,
            iconDescription = stringResource(id = R.string.iconUser),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )

        ProfileItem(
            title = stringResource(id = R.string.lblDescription),
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim adLorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim adLorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad",
            icon = Icons.Outlined.Info,
            iconDescription = stringResource(id = R.string.iconInformation),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )

        ProfileItem(
            title = stringResource(id = R.string.lblEmail),
            description = "s20120154@alumnos.itsur.edu.mx",
            icon = Icons.Outlined.Email,
            iconDescription = stringResource(id = R.string.iconEmail),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )

        ProfileItem(
            title = stringResource(id = R.string.lblPhone),
            description = "(+52) 445 141 1834",
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