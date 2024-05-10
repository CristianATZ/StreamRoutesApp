@file:OptIn(ExperimentalMaterial3Api::class)

package net.streamroutes.sreamroutesapp.ui.start_screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.LockReset
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.navigation.AppScreens
import net.streamroutes.sreamroutesapp.viewmodel.routes.ChangeViewModel

@Composable
fun ChangeScreen(changeViewModel: ChangeViewModel, navController: NavController){
    Change(changeViewModel, navController)
}

@Composable
fun Change(changeViewModel: ChangeViewModel, navController: NavController ){
    val brush = Brush.verticalGradient(
        listOf(Color(0xFFE8AA42), Color(0xFFEACE43))
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(64.dp))

        // logo
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "logo",
            modifier = Modifier.size(100.dp)
        )

        Spacer(modifier = Modifier.size(64.dp))

        // contrasenia
        CustomOutlinedTextField(
            value = changeViewModel.password,
            onValueChange = { changeViewModel.updatePassword(it) },
            placeholderText = stringResource(id = R.string.txtContra),
            leadingIcon = Icons.Filled.Lock,
            imeAction = ImeAction.Next
        )

        Spacer(modifier = Modifier.size(16.dp))

        // confimar contrasenia
        CustomOutlinedTextField(
            value = changeViewModel.confirmPass,
            onValueChange = { changeViewModel.updateConfirmPass(it) },
            placeholderText = stringResource(id = R.string.txtConfrimarContra),
            leadingIcon = Icons.Filled.LockReset,
            imeAction = ImeAction.Done
        )

        // boton cambiar
        Button(
            onClick = {
                navController.navigate(AppScreens.LoginScreen.route)
            },
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                contentColor = MaterialTheme.colorScheme.onTertiaryContainer
            ),
            elevation = ButtonDefaults.elevatedButtonElevation(
                defaultElevation = 4.dp
            ),
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .padding(top = 16.dp)
                .height(50.dp)
        ) {
            Text(
                text = stringResource(id = R.string.btnCambiar),
                style = MaterialTheme.typography.bodyLarge
            )
        }

        // boton cancelar
        Column {
            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    navController.navigate(AppScreens.LoginScreen.route)
                },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.tertiary,
                    contentColor = MaterialTheme.colorScheme.onTertiary
                ),
                elevation = ButtonDefaults.elevatedButtonElevation(
                    defaultElevation = 4.dp
                ),
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(50.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.btnCancelar),
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            Spacer(modifier = Modifier.size(16.dp))
        }
    }
}