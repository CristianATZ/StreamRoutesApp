package net.streamroutes.sreamroutesapp.features.profile.presentation.profile

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.core.domain.model.User
import net.streamroutes.sreamroutesapp.features.profile.components.OutlinedTitleTextField
import net.streamroutes.sreamroutesapp.features.profile.components.ProfileSmallTopAppBar
import net.streamroutes.sreamroutesapp.features.profile.components.TrailingIconWithCalendar
import net.streamroutes.sreamroutesapp.features.profile.components.TrailingIconWithDropdownMenu

@Composable
fun EditInformation(
    profileViewModel: ProfileViewModel,
    modifier: Modifier = Modifier
) {
    // no pasar el modifier, solo en caso de que no se coloree
    // si no se colorea, usar scaffold para encapsular las cosas

    val context = LocalContext.current
    val updateResult by profileViewModel.updateResult.collectAsState()
    val userData by profileViewModel.userData.collectAsState()

    var name by remember {
        mutableStateOf("")
    }

    var lastName by remember {
        mutableStateOf("")
    }

    var address by remember {
        mutableStateOf("")
    }

    var neighborhood by remember {
        mutableStateOf("")
    }

    var numberAddress by remember {
        mutableStateOf("")
    }

    var country by remember {
        mutableStateOf("")
    }

    var state by remember {
        mutableStateOf("")
    }

    var birthday by remember {
        mutableStateOf("")
    }

    var gender by remember {
        mutableStateOf("")
    }

    var description by remember {
        mutableStateOf("")
    }

    // CAMBIAR POR SUS RESPECTIVAS LISTAS
    val countryList = listOf("Mexico", "Canada", "United States", "Germany", "France")
    val genderList = listOf("Masculino", "Femenino", "Indefinido")
    val stateList = listOf("Guanajuato", "Michoacán", "Triste", "aki andamos")


    /**
     * Cambiar valor de las variables en base a userData
     */
    LaunchedEffect(userData) {
        userData?.let {
            name = it.names
            lastName =
                if(it.lastName1.equals("")) ""
                else it.lastName1 + " " + it.lastName2
            address = it.address
            neighborhood = it.neighborhood
            numberAddress = it.numberAddress
            country = it.country
            state = it.state
            birthday = it.birthday
            gender = it.gender
            description = it.description
        }
    }


    /**
     * Cambio de estado de la vriable updateResult para indicar si se pudo o
     * no realizar el cambio de información del usuario
     */
    updateResult?.let { success ->
        LaunchedEffect(success) {
            if(success){
                Toast.makeText(context, "Información actualizada", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "No fue posible actualizar los datos del usuario", Toast.LENGTH_SHORT).show()
            }
        }
    }


    Scaffold(
        topBar = {
            ProfileSmallTopAppBar(
                title = stringResource(id = R.string.lblEditPersonalInformation),
                onBackPressed = {
                    // REGRESAR A EDITAR PERFIL
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.size(16.dp))

            // nombre
            OutlinedTitleTextField(
                title = stringResource(id = R.string.txtName),
                placeholder = stringResource(id = R.string.lblPlaceholderName),
                iconClear = stringResource(id = R.string.iconClearName),
                value = name,
                onValueChange = { n ->
                    name = n
                },
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .align(Alignment.CenterHorizontally)
            )

            // apellidos
            OutlinedTitleTextField(
                title = stringResource(id = R.string.txtLastName),
                placeholder = stringResource(id = R.string.lblPlaceholderLastName),
                iconClear = stringResource(id = R.string.iconClearLastName),
                value = lastName,
                onValueChange = { l ->
                    lastName = l
                },
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .align(Alignment.CenterHorizontally)
            )

            // calle
            OutlinedTitleTextField(
                title = stringResource(id = R.string.txtAddress),
                placeholder = stringResource(id = R.string.lblPlaceholderAddress),
                prefix = stringResource(id = R.string.txtAddressPrefix),
                iconClear = stringResource(id = R.string.iconClearAddress),
                value = address,
                onValueChange = { a ->
                    address = a
                },
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .align(Alignment.CenterHorizontally)
            )

            // colonia
            OutlinedTitleTextField(
                title = stringResource(id = R.string.txtNeighborhood),
                placeholder = stringResource(id = R.string.lblPlacelholderNeighborhood),
                prefix = stringResource(id = R.string.txtNeighborhoodPrefix),
                iconClear = stringResource(id = R.string.iconClearNeighborhood),
                value = neighborhood,
                onValueChange = { n ->
                    neighborhood = n
                },
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .align(Alignment.CenterHorizontally)
            )

            // numero exterior
            OutlinedTitleTextField(
                title = stringResource(id = R.string.txtNumberAddress),
                placeholder = stringResource(id = R.string.lblPlaceholderNumberAddress),
                prefix = stringResource(id = R.string.txtNumberAddressPrefix),
                iconClear = stringResource(id = R.string.iconClearNumberAddress),
                value = numberAddress,
                onValueChange = { n ->
                    numberAddress = n
                },
                keyboardType = KeyboardType.Number,
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .align(Alignment.CenterHorizontally)
            )

            // pais
            OutlinedTitleTextField(
                title = stringResource(id = R.string.txtCountry),
                placeholder = stringResource(id = R.string.lblPlaceholderSelect),
                value = country,
                onValueChange = { c ->
                    country = c
                },
                readOnly = true,
                trailingIcon = {
                    TrailingIconWithDropdownMenu(
                        menuItems = countryList,
                        //icon = Icons.Outlined.
                        iconDescription = stringResource(id = R.string.iconOpenCountryList)
                    ) { c ->
                        country = c
                    }
                },
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .align(Alignment.CenterHorizontally)
            )

            // estado
            OutlinedTitleTextField(
                title = stringResource(id = R.string.txtState),
                placeholder = stringResource(id = R.string.lblPlaceholderSelect),
                value = state,
                onValueChange = { s ->
                    state = s
                },
                readOnly = true,
                trailingIcon = {
                    TrailingIconWithDropdownMenu(
                        menuItems = stateList,
                        //icon = Icons.Outlined.
                        iconDescription = stringResource(id = R.string.iconOpenStateList)
                    ) { s ->
                        state = s
                    }
                },
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .align(Alignment.CenterHorizontally)
            )

            // fecha de nacimiento
            OutlinedTitleTextField(
                title = stringResource(id = R.string.txtBirthday),
                placeholder = stringResource(id = R.string.lblPlaceholderSelect),
                value = birthday,
                onValueChange = { b ->
                    birthday = b
                },
                readOnly = true,
                trailingIcon = {
                    TrailingIconWithCalendar(
                        iconDescription = stringResource(id = R.string.iconOpenCalendar)
                    ) { b ->
                        birthday = b
                    }
                },
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .align(Alignment.CenterHorizontally)
            )

            // genero
            OutlinedTitleTextField(
                title = stringResource(id = R.string.txtGender),
                placeholder = stringResource(id = R.string.lblPlaceholderSelect),
                value = gender,
                onValueChange = { g ->
                    gender = g
                },
                readOnly = true,
                trailingIcon = {
                    TrailingIconWithDropdownMenu(
                        menuItems = genderList,
                        //icon = Icons.Outlined.
                        iconDescription = stringResource(id = R.string.iconOpenGenderList)
                    ) { g ->
                        gender =  g
                    }
                },
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .align(Alignment.CenterHorizontally)
            )

            // descripcion
            OutlinedTitleTextField(
                title = stringResource(id = R.string.txtDescription),
                placeholder = stringResource(id = R.string.lblPlaceholderDescription),
                iconClear = stringResource(id = R.string.iconClearDescription),
                singleLine = false,
                value = description,
                onValueChange = { d ->
                    description = d
                },
                imeAction = ImeAction.Done,
                onDone = {
                    // ACTUALIAZR DATOS DE FIBREASE
                },
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth(0.9f)
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.size(16.dp))

            // regresar a reditar perfil
            OutlinedButton(
                onClick = {
                    // REGRESAR A EDITAR PERFIL
                },
                shape = shapes.small,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth(0.9f)
            ) {
                Text(text = stringResource(id = R.string.btnBack))
            }

            // guardar informacion
            Button(
                onClick = {
                    // ACTUALIZAR DATOS EN FIREBASE
                    val parts = lastName.split(" ")
                    val updUser = User(
                        names = name,
                        lastName1 = parts[0],
                        lastName2 = if (parts.size > 1) parts[1] else "",
                        address = address,
                        neighborhood = neighborhood,
                        numberAddress = numberAddress,
                        country = country,
                        state = state,
                        birthday = birthday,
                        gender = gender,
                        description = description
                    )
                    profileViewModel.updateUserData(updUser)
                },
                shape = shapes.small,
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth(0.9f)
            ) {
                Text(text = stringResource(id = R.string.btnSave))
            }
        }
    }
}