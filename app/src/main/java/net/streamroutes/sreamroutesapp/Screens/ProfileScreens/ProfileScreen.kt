package net.streamroutes.sreamroutesapp.Screens.ProfileScreens

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import net.streamroutes.sreamroutesapp.Navigation.AppScreens
import net.streamroutes.sreamroutesapp.R

data class DataInfoItem(val title: String, val inf: String)

@Composable
fun ProfileScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeaderProfile(navController)

            FeaturedProfile()
        }
    }
}

@Composable
fun FooterProfile(

) {
    var user by remember {
        mutableStateOf(false)
    }

    var member by remember {
        mutableStateOf(false)
    }

    var seguridad by remember {
        mutableStateOf(false)
    }

    var userInfo by remember {
        mutableStateOf(
            listOf(
                DataInfoItem("Cumpleaños","Mie, 27 Nov 2002"),
                DataInfoItem("Telefono","445 141 1834"),
                DataInfoItem("Pais","Mexico"),
                DataInfoItem("Ciudad","Moroleon, Gto")
            )
        )
    }

    var secInfo by remember {
        mutableStateOf(
            listOf(
                DataInfoItem("Contraseña","Vigente"),
                DataInfoItem("Verificacion", "Activada"),
                DataInfoItem("Correo", "Autenticado"),
                DataInfoItem("Telefono", "Autenticado")
            )
        )
    }

    var memberInfo by remember {
        mutableStateOf(
            listOf(
                DataInfoItem("Tipo","Estudiantil"),
                DataInfoItem("Duracion","Mensual"),
                DataInfoItem("Corte","Vie, 29 Sep 2023"),
            )
        )
    }

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        var openUse by remember {
            mutableStateOf(false)
        }

        var openMem by remember {
            mutableStateOf(false)
        }

        var openSec by remember {
            mutableStateOf(false)
        }

        if(openUse){
            UserDialogEdit(
                onClose = { openUse = !openUse }
            )
        }

        if(openMem){
            MembDialogEdit(
                onClose = { openMem = !openMem }
            )
        }

        if(openSec){
            SecuDialogEdit(
                onClose = { openSec = !openSec }
            )
        }

        ProfileItems(
            title = "Informacion personal",
            description = "Fecha nac, pais, telefono, etc.",
            open = user,
            items = userInfo,
            onClick = {
                user = !user
            },
            onEdit = { openUse = !openUse }
        )

        Spacer(modifier = Modifier.size(8.dp))

        ProfileItems(
            title = "Membresia",
            description = "Tipo, duracion, corte, etc.",
            open = member,
            items = memberInfo,
            onClick = { member = !member },
            onEdit = { openMem = !openMem }
        )

        Spacer(modifier = Modifier.size(8.dp))

        ProfileItems(
            title = "Seguridad",
            description = "Contraseña, verificacion en dos pasos.",
            open = seguridad,
            items = secInfo,
            onClick = { seguridad = !seguridad },
            onEdit = { openSec = !openSec }
        )

        Spacer(modifier = Modifier.size(16.dp))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecuDialogEdit(
    onClose: () -> Unit
) {
    var changePhone by remember {
        mutableStateOf(false)
    }

    var sendCodePhone by remember {
        mutableStateOf(false)
    }

    var codeCorrectPhone by remember {
        mutableStateOf(false)
    }

    var changeEmail by remember {
        mutableStateOf(false)
    }

    var sendCodeEmail by remember {
        mutableStateOf(false)
    }

    var codeCorrectEmail by remember {
        mutableStateOf(false)
    }

    var changePass by remember {
        mutableStateOf(false)
    }

    var checkPass by remember {
        mutableStateOf(false)
    }

    Dialog(
        onDismissRequest = { /*TODO*/ },
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        Card(

        ) {
            HeaderDialog(
                onClose = { onClose() }
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {

                // telefono actual
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    if(!changePhone){
                        OutlinedTextField(
                            value = "445 141 1834",
                            onValueChange = {},
                            label = {
                                Text(
                                    text = "Telefono",
                                    color = MaterialTheme.colorScheme.onTertiaryContainer
                                )
                            },
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                disabledTextColor = MaterialTheme.colorScheme.onTertiaryContainer,
                                disabledBorderColor = MaterialTheme.colorScheme.outline
                            ),
                            enabled = false,
                            modifier = Modifier
                                .weight(1f)
                        )
                    } else {
                        Column {
                            OutlinedTextField(
                                value = "",
                                onValueChange = {},
                                label = {
                                    Text(
                                        text = "Nuevo telefono",
                                        color = MaterialTheme.colorScheme.onTertiaryContainer
                                    )
                                },
                                trailingIcon = {
                                    IconButton(onClick = { /*TODO*/ }) {
                                        Icon(
                                            imageVector = Icons.Filled.Clear,
                                            contentDescription = null,
                                            tint = MaterialTheme.colorScheme.onTertiaryContainer
                                        )
                                    }
                                },
                                colors = TextFieldDefaults.outlinedTextFieldColors(
                                    textColor = MaterialTheme.colorScheme.onTertiaryContainer,
                                    cursorColor = MaterialTheme.colorScheme.onTertiaryContainer,
                                    focusedBorderColor = MaterialTheme.colorScheme.tertiary,
                                    unfocusedBorderColor = MaterialTheme.colorScheme.outline
                                ),
                                keyboardOptions = KeyboardOptions(
                                    imeAction = ImeAction.Next
                                ),
                                modifier = Modifier
                                    .fillMaxWidth(1f)
                                    .padding(vertical = 8.dp)
                            )

                            TextButton(
                                onClick = { sendCodePhone = !sendCodePhone },
                                modifier = Modifier
                                    .fillMaxWidth(1f)
                                    .height(50.dp)
                            ) {
                                Text(
                                    text = "Enviar codigo",
                                    color = MaterialTheme.colorScheme.onTertiaryContainer
                                )
                            }

                            if(sendCodePhone){
                                OutlinedTextField(
                                    value = "",
                                    onValueChange = {},
                                    label = {
                                        Text(
                                            text = "Codigo",
                                            color = MaterialTheme.colorScheme.onTertiaryContainer
                                        )
                                    },
                                    trailingIcon = {
                                        IconButton(onClick = { /*TODO*/ }) {
                                            Icon(
                                                imageVector = Icons.Filled.Clear,
                                                contentDescription = null,
                                                tint = MaterialTheme.colorScheme.onTertiaryContainer
                                            )
                                        }
                                    },
                                    colors = TextFieldDefaults.outlinedTextFieldColors(
                                        textColor = MaterialTheme.colorScheme.onTertiaryContainer,
                                        cursorColor = MaterialTheme.colorScheme.onTertiaryContainer,
                                        focusedBorderColor = MaterialTheme.colorScheme.tertiary,
                                        unfocusedBorderColor = MaterialTheme.colorScheme.outline
                                    ),
                                    keyboardOptions = KeyboardOptions(
                                        imeAction = ImeAction.Next
                                    ),
                                    modifier = Modifier
                                        .fillMaxWidth(1f)
                                        .padding(vertical = 8.dp)
                                )

                                TextButton(
                                    onClick = { codeCorrectPhone = true },
                                    modifier = Modifier
                                        .fillMaxWidth(1f)
                                        .height(50.dp)
                                ) {
                                    Text(
                                        text = "Verificar codigo",
                                        color = MaterialTheme.colorScheme.onTertiaryContainer
                                    )
                                }

                                if(codeCorrectPhone){
                                    OutlinedTextField(
                                        value = "",
                                        onValueChange = {},
                                        label = {
                                            Text(
                                                text = "Nuevo telefono",
                                                color = MaterialTheme.colorScheme.onTertiaryContainer
                                            )
                                        },
                                        trailingIcon = {
                                            IconButton(onClick = { /*TODO*/ }) {
                                                Icon(
                                                    imageVector = Icons.Filled.Clear,
                                                    contentDescription = null,
                                                    tint = MaterialTheme.colorScheme.onTertiaryContainer
                                                )
                                            }
                                        },
                                        colors = TextFieldDefaults.outlinedTextFieldColors(
                                            textColor = MaterialTheme.colorScheme.onTertiaryContainer,
                                            cursorColor = MaterialTheme.colorScheme.onTertiaryContainer,
                                            focusedBorderColor = MaterialTheme.colorScheme.tertiary,
                                            unfocusedBorderColor = MaterialTheme.colorScheme.outline
                                        ),
                                        keyboardOptions = KeyboardOptions(
                                            imeAction = ImeAction.Next
                                        ),
                                        modifier = Modifier
                                            .fillMaxWidth(1f)
                                            .padding(vertical = 8.dp)
                                    )

                                    OutlinedTextField(
                                        value = "",
                                        onValueChange = {},
                                        label = {
                                            Text(
                                                text = "Confirmar telefono",
                                                color = MaterialTheme.colorScheme.onTertiaryContainer
                                            )
                                        },
                                        trailingIcon = {
                                            IconButton(onClick = { /*TODO*/ }) {
                                                Icon(
                                                    imageVector = Icons.Filled.Clear,
                                                    contentDescription = null,
                                                    tint = MaterialTheme.colorScheme.onTertiaryContainer
                                                )
                                            }
                                        },
                                        colors = TextFieldDefaults.outlinedTextFieldColors(
                                            textColor = MaterialTheme.colorScheme.onTertiaryContainer,
                                            cursorColor = MaterialTheme.colorScheme.onTertiaryContainer,
                                            focusedBorderColor = MaterialTheme.colorScheme.tertiary,
                                            unfocusedBorderColor = MaterialTheme.colorScheme.outline
                                        ),
                                        keyboardOptions = KeyboardOptions(
                                            imeAction = ImeAction.Done
                                        ),
                                        modifier = Modifier
                                            .fillMaxWidth(1f)
                                            .padding(vertical = 8.dp)
                                    )

                                    Button(
                                        onClick = { /*TODO*/ },
                                        modifier = Modifier
                                            .fillMaxWidth(1f)
                                            .height(50.dp),
                                        shape = RoundedCornerShape(15.dp),
                                        elevation = ButtonDefaults.elevatedButtonElevation(
                                            defaultElevation = 5.dp
                                        ),
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = MaterialTheme.colorScheme.tertiary
                                        )
                                    ) {
                                        Text(
                                            text = "Cambiar telefono",
                                            color = MaterialTheme.colorScheme.onTertiary
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                // cambiar telefono
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ){
                    TextButton(
                        onClick = { changePhone = !changePhone },
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp)
                    ) {
                        Text(
                            text = if(!changePhone) "Cambiar telefono" else "Cancelar",
                            color = MaterialTheme.colorScheme.onTertiaryContainer
                        )
                    }
                }

                Divider(modifier = Modifier.padding(bottom = 16.dp))

                // correo actual
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    if(!changeEmail){
                        OutlinedTextField(
                            value = "s20120154@alumnos.itsur.edu.mx",
                            onValueChange = {},
                            label = {
                                Text(
                                    text = "Correo",
                                    color = MaterialTheme.colorScheme.onTertiaryContainer
                                )
                            },
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                disabledTextColor = MaterialTheme.colorScheme.onTertiaryContainer,
                                disabledBorderColor = MaterialTheme.colorScheme.outline
                            ),
                            enabled = false,
                            modifier = Modifier
                                .weight(1f)
                        )
                    } else {
                        Column {
                            OutlinedTextField(
                                value = "",
                                onValueChange = {},
                                label = {
                                    Text(
                                        text = "Nuevo correo",
                                        color = MaterialTheme.colorScheme.onTertiaryContainer
                                    )
                                },
                                trailingIcon = {
                                    IconButton(onClick = { /*TODO*/ }) {
                                        Icon(
                                            imageVector = Icons.Filled.Clear,
                                            contentDescription = null,
                                            tint = MaterialTheme.colorScheme.onTertiaryContainer
                                        )
                                    }
                                },
                                colors = TextFieldDefaults.outlinedTextFieldColors(
                                    textColor = MaterialTheme.colorScheme.onTertiaryContainer,
                                    cursorColor = MaterialTheme.colorScheme.onTertiaryContainer,
                                    focusedBorderColor = MaterialTheme.colorScheme.tertiary,
                                    unfocusedBorderColor = MaterialTheme.colorScheme.outline
                                ),
                                keyboardOptions = KeyboardOptions(
                                    imeAction = ImeAction.Next
                                ),
                                modifier = Modifier
                                    .fillMaxWidth(1f)
                                    .padding(vertical = 8.dp)
                            )

                            TextButton(
                                onClick = { sendCodeEmail = !sendCodeEmail },
                                modifier = Modifier
                                    .fillMaxWidth(1f)
                                    .height(50.dp)
                            ) {
                                Text(
                                    text = "Enviar codigo",
                                    color = MaterialTheme.colorScheme.onTertiaryContainer
                                )
                            }

                            if(sendCodeEmail){
                                OutlinedTextField(
                                    value = "",
                                    onValueChange = {},
                                    label = {
                                        Text(
                                            text = "Codigo",
                                            color = MaterialTheme.colorScheme.onTertiaryContainer
                                        )
                                    },
                                    trailingIcon = {
                                        IconButton(onClick = { /*TODO*/ }) {
                                            Icon(
                                                imageVector = Icons.Filled.Clear,
                                                contentDescription = null,
                                                tint = MaterialTheme.colorScheme.onTertiaryContainer
                                            )
                                        }
                                    },
                                    colors = TextFieldDefaults.outlinedTextFieldColors(
                                        textColor = MaterialTheme.colorScheme.onTertiaryContainer,
                                        cursorColor = MaterialTheme.colorScheme.onTertiaryContainer,
                                        focusedBorderColor = MaterialTheme.colorScheme.tertiary,
                                        unfocusedBorderColor = MaterialTheme.colorScheme.outline
                                    ),
                                    keyboardOptions = KeyboardOptions(
                                        imeAction = ImeAction.Next
                                    ),
                                    modifier = Modifier
                                        .fillMaxWidth(1f)
                                        .padding(vertical = 8.dp)
                                )

                                TextButton(
                                    onClick = { codeCorrectEmail = true },
                                    modifier = Modifier
                                        .fillMaxWidth(1f)
                                        .height(50.dp)
                                ) {
                                    Text(
                                        text = "Verificar codigo",
                                        color = MaterialTheme.colorScheme.onTertiaryContainer
                                    )
                                }

                                if(codeCorrectEmail){
                                    OutlinedTextField(
                                        value = "",
                                        onValueChange = {},
                                        label = {
                                            Text(
                                                text = "Nuevo correo",
                                                color = MaterialTheme.colorScheme.onTertiaryContainer
                                            )
                                        },
                                        trailingIcon = {
                                            IconButton(onClick = { /*TODO*/ }) {
                                                Icon(
                                                    imageVector = Icons.Filled.Clear,
                                                    contentDescription = null,
                                                    tint = MaterialTheme.colorScheme.onTertiaryContainer
                                                )
                                            }
                                        },
                                        colors = TextFieldDefaults.outlinedTextFieldColors(
                                            textColor = MaterialTheme.colorScheme.onTertiaryContainer,
                                            cursorColor = MaterialTheme.colorScheme.onTertiaryContainer,
                                            focusedBorderColor = MaterialTheme.colorScheme.tertiary,
                                            unfocusedBorderColor = MaterialTheme.colorScheme.outline
                                        ),
                                        keyboardOptions = KeyboardOptions(
                                            imeAction = ImeAction.Next
                                        ),
                                        modifier = Modifier
                                            .fillMaxWidth(1f)
                                            .padding(vertical = 8.dp)
                                    )

                                    OutlinedTextField(
                                        value = "",
                                        onValueChange = {},
                                        label = {
                                            Text(
                                                text = "Confirmar correo",
                                                color = MaterialTheme.colorScheme.onTertiaryContainer
                                            )
                                        },
                                        trailingIcon = {
                                            IconButton(onClick = { /*TODO*/ }) {
                                                Icon(
                                                    imageVector = Icons.Filled.Clear,
                                                    contentDescription = null,
                                                    tint = MaterialTheme.colorScheme.onTertiaryContainer
                                                )
                                            }
                                        },
                                        colors = TextFieldDefaults.outlinedTextFieldColors(
                                            textColor = MaterialTheme.colorScheme.onTertiaryContainer,
                                            cursorColor = MaterialTheme.colorScheme.onTertiaryContainer,
                                            focusedBorderColor = MaterialTheme.colorScheme.tertiary,
                                            unfocusedBorderColor = MaterialTheme.colorScheme.outline
                                        ),
                                        keyboardOptions = KeyboardOptions(
                                            imeAction = ImeAction.Done
                                        ),
                                        modifier = Modifier
                                            .fillMaxWidth(1f)
                                            .padding(vertical = 8.dp)
                                    )

                                    Button(
                                        onClick = { /*TODO*/ },
                                        modifier = Modifier
                                            .fillMaxWidth(1f)
                                            .height(50.dp),
                                        shape = RoundedCornerShape(15.dp),
                                        elevation = ButtonDefaults.elevatedButtonElevation(
                                            defaultElevation = 5.dp
                                        ),
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = MaterialTheme.colorScheme.tertiary
                                        )
                                    ) {
                                        Text(
                                            text = "Cambiar correo",
                                            color = MaterialTheme.colorScheme.onTertiary
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                // cambiar correo
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ){
                    TextButton(
                        onClick = { changeEmail = !changeEmail },
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp)
                    ) {
                        Text(
                            text = if(!changeEmail) "Cambiar correo" else "Cancelar",
                            color = MaterialTheme.colorScheme.onTertiaryContainer
                        )
                    }
                }

                Divider(modifier = Modifier.padding(bottom = 16.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    if(changePass){
                        Column {
                            OutlinedTextField(
                                value = "",
                                onValueChange = {},
                                label = {
                                    Text(
                                        text = "Contrasenia actual",
                                        color = MaterialTheme.colorScheme.onTertiaryContainer
                                    )
                                },
                                trailingIcon = {
                                    IconButton(onClick = { /*TODO*/ }) {
                                        Icon(
                                            imageVector = Icons.Filled.Clear,
                                            contentDescription = null,
                                            tint = MaterialTheme.colorScheme.onTertiaryContainer
                                        )
                                    }
                                },
                                colors = TextFieldDefaults.outlinedTextFieldColors(
                                    textColor = MaterialTheme.colorScheme.onTertiaryContainer,
                                    cursorColor = MaterialTheme.colorScheme.onTertiaryContainer,
                                    focusedBorderColor = MaterialTheme.colorScheme.tertiary,
                                    unfocusedBorderColor = MaterialTheme.colorScheme.outline
                                ),
                                keyboardOptions = KeyboardOptions(
                                    imeAction = ImeAction.Next
                                ),
                                modifier = Modifier
                                    .fillMaxWidth(1f)
                                    .padding(vertical = 8.dp)
                            )

                            TextButton(
                                onClick = { checkPass = !checkPass },
                                modifier = Modifier
                                    .fillMaxWidth(1f)
                                    .height(50.dp)
                            ) {
                                Text(
                                    text = "Verificar contrasenia",
                                    color = MaterialTheme.colorScheme.onTertiaryContainer
                                )
                            }

                            if(checkPass){
                                OutlinedTextField(
                                    value = "",
                                    onValueChange = {},
                                    label = {
                                        Text(
                                            text = "Contrasenia nueva",
                                            color = MaterialTheme.colorScheme.onTertiaryContainer
                                        )
                                    },
                                    trailingIcon = {
                                        IconButton(onClick = { /*TODO*/ }) {
                                            Icon(
                                                imageVector = Icons.Filled.Clear,
                                                contentDescription = null,
                                                tint = MaterialTheme.colorScheme.onTertiaryContainer
                                            )
                                        }
                                    },
                                    colors = TextFieldDefaults.outlinedTextFieldColors(
                                        textColor = MaterialTheme.colorScheme.onTertiaryContainer,
                                        cursorColor = MaterialTheme.colorScheme.onTertiaryContainer,
                                        focusedBorderColor = MaterialTheme.colorScheme.tertiary,
                                        unfocusedBorderColor = MaterialTheme.colorScheme.outline
                                    ),
                                    keyboardOptions = KeyboardOptions(
                                        imeAction = ImeAction.Next
                                    ),
                                    modifier = Modifier
                                        .fillMaxWidth(1f)
                                        .padding(vertical = 8.dp)
                                )

                                OutlinedTextField(
                                    value = "",
                                    onValueChange = {},
                                    label = {
                                        Text(
                                            text = "Confirmar contrasenia",
                                            color = MaterialTheme.colorScheme.onTertiaryContainer
                                        )
                                    },
                                    trailingIcon = {
                                        IconButton(onClick = { /*TODO*/ }) {
                                            Icon(
                                                imageVector = Icons.Filled.Clear,
                                                contentDescription = null,
                                                tint = MaterialTheme.colorScheme.onTertiaryContainer
                                            )
                                        }
                                    },
                                    colors = TextFieldDefaults.outlinedTextFieldColors(
                                        textColor = MaterialTheme.colorScheme.onTertiaryContainer,
                                        cursorColor = MaterialTheme.colorScheme.onTertiaryContainer,
                                        focusedBorderColor = MaterialTheme.colorScheme.tertiary,
                                        unfocusedBorderColor = MaterialTheme.colorScheme.outline
                                    ),
                                    keyboardOptions = KeyboardOptions(
                                        imeAction = ImeAction.Next
                                    ),
                                    modifier = Modifier
                                        .fillMaxWidth(1f)
                                        .padding(vertical = 8.dp)
                                )

                                TextButton(
                                    onClick = { /* TODO */ },
                                    modifier = Modifier
                                        .fillMaxWidth(1f)
                                        .height(50.dp)
                                ) {
                                    Text(
                                        text = "Cambiar contrasenia",
                                        color = MaterialTheme.colorScheme.onTertiaryContainer
                                    )
                                }

                            }
                        }
                    }
                }

                // cambiar correo
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ){
                    Button(
                        onClick = { changePass = !changePass },
                        modifier = Modifier
                            .fillMaxWidth(1f)
                            .height(50.dp),
                        shape = RoundedCornerShape(15.dp),
                        elevation = ButtonDefaults.elevatedButtonElevation(
                            defaultElevation = 5.dp
                        ),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.tertiary
                        )
                    ) {
                        Text(
                            text = if(changePass) "Cancelar" else "Cambiar contrasenia",
                            color = MaterialTheme.colorScheme.onTertiary
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MembDialogEdit(
    onClose: () -> Unit
) {
    Dialog(
        onDismissRequest = { /*TODO*/ },
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        Card(

        ) {
            HeaderDialog(
                onClose = { onClose() }
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {

                // id de membresia
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    OutlinedTextField(
                        value = "1234",
                        onValueChange = {},
                        label = {
                            Text(
                                text = "Id. Membresia",
                                color = MaterialTheme.colorScheme.onTertiaryContainer
                            )
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            disabledTextColor = MaterialTheme.colorScheme.onTertiaryContainer,
                            disabledBorderColor = MaterialTheme.colorScheme.outline
                        ),
                        enabled = false,
                        modifier = Modifier
                            .weight(1f)
                    )

                }

                // estatus de la membresia
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Estatus",
                        color = MaterialTheme.colorScheme.onTertiaryContainer
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    AssistChip(
                        onClick = { },
                        label = {
                            Text(
                                text = "Activo",
                                color = MaterialTheme.colorScheme.onTertiary
                            )
                        },
                        colors = AssistChipDefaults.assistChipColors(
                            containerColor = MaterialTheme.colorScheme.tertiary,
                        ),
                        border = AssistChipDefaults.assistChipBorder(
                            borderColor = MaterialTheme.colorScheme.tertiary
                        )
                    )
                }

                // usuario
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    OutlinedTextField(
                        value = "CristianToZa",
                        onValueChange = {},
                        label = {
                            Text(
                                text = "Usuario",
                                color = MaterialTheme.colorScheme.onTertiaryContainer
                            )
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            disabledTextColor = MaterialTheme.colorScheme.onTertiaryContainer,
                            disabledBorderColor = MaterialTheme.colorScheme.outline
                        ),
                        enabled = false,
                        modifier = Modifier
                            .weight(1f)
                    )

                }

                // inicio
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    OutlinedTextField(
                        value = "Vie 29 Sep 2023",
                        onValueChange = {},
                        label = {
                            Text(
                                text = "Inicio",
                                color = MaterialTheme.colorScheme.onTertiaryContainer
                            )
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            disabledTextColor = MaterialTheme.colorScheme.onTertiaryContainer,
                            disabledBorderColor = MaterialTheme.colorScheme.outline
                        ),
                        enabled = false,
                        modifier = Modifier
                            .weight(1f)
                    )

                }

                // expiracion
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    OutlinedTextField(
                        value = "Dom 29 Oct 2023",
                        onValueChange = {},
                        label = {
                            Text(
                                text = "Expiracion",
                                color = MaterialTheme.colorScheme.onTertiaryContainer
                            )
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            disabledTextColor = MaterialTheme.colorScheme.onTertiaryContainer,
                            disabledBorderColor = MaterialTheme.colorScheme.outline
                        ),
                        enabled = false,
                        modifier = Modifier
                            .weight(1f)
                    )

                }

                // tipo membresia
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    OutlinedTextField(
                        value = "Mensual (Estudiantil)",
                        onValueChange = {},
                        label = {
                            Text(
                                text = "Tipo membresia",
                                color = MaterialTheme.colorScheme.onTertiaryContainer
                            )
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            disabledTextColor = MaterialTheme.colorScheme.onTertiaryContainer,
                            disabledBorderColor = MaterialTheme.colorScheme.outline
                        ),
                        enabled = false,
                        modifier = Modifier
                            .weight(1f)
                    )

                }

                // moneda
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    OutlinedTextField(
                        value = "MXN (Peso mexicano)",
                        onValueChange = {},
                        label = {
                            Text(
                                text = "Moneda pago",
                                color = MaterialTheme.colorScheme.onTertiaryContainer
                            )
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            disabledTextColor = MaterialTheme.colorScheme.onTertiaryContainer,
                            disabledBorderColor = MaterialTheme.colorScheme.outline
                        ),
                        enabled = false,
                        modifier = Modifier
                            .weight(1f)
                    )

                }

                // mejorar suscripcion
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ){
                    TextButton(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp)
                    ) {
                        Text(
                            text = "Mejorar suscripcion",
                            color = MaterialTheme.colorScheme.onTertiaryContainer
                        )
                    }
                }

                // cancelar suscripcion
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Button(
                        onClick = { onClose() },
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp),
                        shape = RoundedCornerShape(15.dp),
                        elevation = ButtonDefaults.elevatedButtonElevation(
                            defaultElevation = 5.dp
                        ),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.tertiary
                        )
                    ) {
                        Text(
                            text = "Cancelar suscripcion",
                            color = MaterialTheme.colorScheme.onTertiary
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDialogEdit(
    onClose: () -> Unit
) {
    Dialog(
        onDismissRequest = { /*TODO*/ },
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        Card(

        ) {
            HeaderDialog(
                onClose = { onClose() }
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {

                // nombre y apellidos
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        label = {
                            Text(
                                text = "Nombre",
                                color = MaterialTheme.colorScheme.onTertiaryContainer
                            )
                        },
                        trailingIcon = {
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(
                                    imageVector = Icons.Filled.Clear,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onTertiaryContainer
                                )
                            }
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            textColor = MaterialTheme.colorScheme.onTertiaryContainer,
                            cursorColor = MaterialTheme.colorScheme.onTertiaryContainer,
                            focusedBorderColor = MaterialTheme.colorScheme.tertiary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outline
                        ),
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next
                        ),
                        modifier = Modifier
                            .weight(1f)
                    )

                    Spacer(modifier = Modifier.size(16.dp))

                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        label = {
                            Text(
                                text = "Apellidos",
                                color = MaterialTheme.colorScheme.onTertiaryContainer
                            )
                        },
                        trailingIcon = {
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(
                                    imageVector = Icons.Filled.Clear,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onTertiaryContainer
                                )
                            }
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            textColor = MaterialTheme.colorScheme.onTertiaryContainer,
                            cursorColor = MaterialTheme.colorScheme.onTertiaryContainer,
                            focusedBorderColor = MaterialTheme.colorScheme.tertiary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outline
                        ),
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next
                        ),
                        modifier = Modifier
                            .weight(1f)
                    )
                }

                // correo electronico
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        label = {
                            Text(
                                text = "Correo electronico",
                                color = MaterialTheme.colorScheme.onTertiaryContainer
                            )
                        },
                        trailingIcon = {
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(
                                    imageVector = Icons.Filled.Clear,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onTertiaryContainer
                                )
                            }
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            textColor = MaterialTheme.colorScheme.onTertiaryContainer,
                            cursorColor = MaterialTheme.colorScheme.onTertiaryContainer,
                            focusedBorderColor = MaterialTheme.colorScheme.tertiary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outline
                        ),
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next
                        ),
                        modifier = Modifier
                            .weight(1f)
                    )
                }

                // pais y etado
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        label = {
                            Text(
                                text = "Pais",
                                color = MaterialTheme.colorScheme.onTertiaryContainer
                            )
                        },
                        trailingIcon = {
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(
                                    imageVector = Icons.Filled.Clear,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onTertiaryContainer
                                )
                            }
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            textColor = MaterialTheme.colorScheme.onTertiaryContainer,
                            cursorColor = MaterialTheme.colorScheme.onTertiaryContainer,
                            focusedBorderColor = MaterialTheme.colorScheme.tertiary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outline
                        ),
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next
                        ),
                        modifier = Modifier
                            .weight(1f)
                    )

                    Spacer(modifier = Modifier.size(16.dp))

                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        label = {
                            Text(
                                text = "Estado",
                                color = MaterialTheme.colorScheme.onTertiaryContainer
                            )
                        },
                        trailingIcon = {
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(
                                    imageVector = Icons.Filled.Clear,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onTertiaryContainer
                                )
                            }
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            textColor = MaterialTheme.colorScheme.onTertiaryContainer,
                            cursorColor = MaterialTheme.colorScheme.onTertiaryContainer,
                            focusedBorderColor = MaterialTheme.colorScheme.tertiary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outline
                        ),
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next
                        ),
                        modifier = Modifier
                            .weight(1f)
                    )
                }

                // direccion y num interior
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        label = {
                            Text(
                                text = "Direccion",
                                color = MaterialTheme.colorScheme.onTertiaryContainer
                            )
                        },
                        trailingIcon = {
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(
                                    imageVector = Icons.Filled.Clear,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onTertiaryContainer
                                )
                            }
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            textColor = MaterialTheme.colorScheme.onTertiaryContainer,
                            cursorColor = MaterialTheme.colorScheme.onTertiaryContainer,
                            focusedBorderColor = MaterialTheme.colorScheme.tertiary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outline
                        ),
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next
                        ),
                        modifier = Modifier
                            .weight(2f)
                    )

                    Spacer(modifier = Modifier.size(16.dp))

                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        label = {
                            Text(
                                text = "# Int.",
                                color = MaterialTheme.colorScheme.onTertiaryContainer
                            )
                        },
                        trailingIcon = {
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(
                                    imageVector = Icons.Filled.Clear,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onTertiaryContainer
                                )
                            }
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            textColor = MaterialTheme.colorScheme.onTertiaryContainer,
                            cursorColor = MaterialTheme.colorScheme.onTertiaryContainer,
                            focusedBorderColor = MaterialTheme.colorScheme.tertiary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outline
                        ),
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next
                        ),
                        modifier = Modifier
                            .weight(1f)
                    )
                }

                // colonia y codigo postal
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        label = {
                            Text(
                                text = "Colonia",
                                color = MaterialTheme.colorScheme.onTertiaryContainer
                            )
                        },
                        trailingIcon = {
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(
                                    imageVector = Icons.Filled.Clear,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onTertiaryContainer
                                )
                            }
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            textColor = MaterialTheme.colorScheme.onTertiaryContainer,
                            cursorColor = MaterialTheme.colorScheme.onTertiaryContainer,
                            focusedBorderColor = MaterialTheme.colorScheme.tertiary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outline
                        ),
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next
                        ),
                        modifier = Modifier
                            .weight(1f)
                    )

                    Spacer(modifier = Modifier.size(16.dp))

                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        label = {
                            Text(
                                text = "CP",
                                color = MaterialTheme.colorScheme.onTertiaryContainer
                            )
                        },
                        trailingIcon = {
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(
                                    imageVector = Icons.Filled.Clear,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onTertiaryContainer
                                )
                            }
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            textColor = MaterialTheme.colorScheme.onTertiaryContainer,
                            cursorColor = MaterialTheme.colorScheme.onTertiaryContainer,
                            focusedBorderColor = MaterialTheme.colorScheme.tertiary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outline
                        ),
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next
                        ),
                        modifier = Modifier
                            .weight(1f)
                    )
                }

                // cumpleaños y genero
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        label = {
                            Text(
                                text = "Cumpleaños",
                                color = MaterialTheme.colorScheme.onTertiaryContainer
                            )
                        },
                        trailingIcon = {
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(
                                    imageVector = Icons.Filled.Clear,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onTertiaryContainer
                                )
                            }
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            textColor = MaterialTheme.colorScheme.onTertiaryContainer,
                            cursorColor = MaterialTheme.colorScheme.onTertiaryContainer,
                            focusedBorderColor = MaterialTheme.colorScheme.tertiary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outline
                        ),
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next
                        ),
                        modifier = Modifier
                            .weight(1f)
                    )

                    Spacer(modifier = Modifier.size(16.dp))

                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        label = {
                            Text(
                                text = "Genero",
                                color = MaterialTheme.colorScheme.onTertiaryContainer
                            )
                        },
                        trailingIcon = {
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(
                                    imageVector = Icons.Filled.Clear,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onTertiaryContainer
                                )
                            }
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(

                        ),
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next
                        ),
                        modifier = Modifier
                            .weight(1f)
                    )
                }

                // ocupacion
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        label = {
                            Text(
                                text = "Ocupacion",
                                color = MaterialTheme.colorScheme.onTertiaryContainer
                            )
                        },
                        trailingIcon = {
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(
                                    imageVector = Icons.Filled.Clear,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onTertiaryContainer
                                )
                            }
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            textColor = MaterialTheme.colorScheme.onTertiaryContainer,
                            cursorColor = MaterialTheme.colorScheme.onTertiaryContainer,
                            focusedBorderColor = MaterialTheme.colorScheme.tertiary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outline
                        ),
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done
                        ),
                        modifier = Modifier
                            .weight(1f)
                    )
                }

                // guardar cambios
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Button(
                        onClick = { onClose() },
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp),
                        shape = RoundedCornerShape(15.dp),
                        elevation = ButtonDefaults.elevatedButtonElevation(
                            defaultElevation = 5.dp
                        ),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.tertiary
                        )
                    ) {
                        Text(
                            text = "Guardar informacion",
                            color = MaterialTheme.colorScheme.onTertiary
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun HeaderDialog(
    onClose: () -> Unit
) {
    Row {
        IconButton(
            onClick = { onClose() },
            modifier = Modifier
                .size(64.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.Close,
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp),
                //tint =
            )
        }
    }
}

@Composable
fun FeaturedProfile(

) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface),
        verticalArrangement = Arrangement.Center,
    ) {

        Spacer(modifier = Modifier.size(24.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            FeaturedInformation(inf = "0", title = "Rutas favoritas")

            FeaturedInformation(inf = "Estudiante",title = "Ocupacion")

            FeaturedInformation(icon = Icons.Outlined.Check,title = "Verificacion dos pasos")
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            FeaturedInformation(inf = "Entretenimeinto, comida, ropa",title = "Intereses", large = true)

            FeaturedInformation(icon = Icons.Outlined.ExitToApp,title = "Cerrar sesion")
        }

        FooterProfile()

    }
}

@Composable
fun FeaturedInformation(
    inf: String = "",
    icon: ImageVector = Icons.Outlined.Refresh,
    title: String,
    large: Boolean = false
) {
    Card(
        shape = RoundedCornerShape(0),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        modifier = Modifier
            .padding(8.dp)
            .width(if (!large) 125.dp else 266.dp)
            .height(100.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if( !inf.isEmpty() ){
                Text(
                    text = inf,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color =  Color(0xFFE8AA42),
                    textAlign = TextAlign.Center,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = if(large) 2 else 1,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            } else {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = Color(0xFFE8AA42),
                    modifier = Modifier
                        .size(32.dp)
                )
            }

            Text(
                text = title,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                letterSpacing = 1.sp,
                lineHeight = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun ProfileItems(
    title: String,
    description: String,
    open: Boolean,
    items: List<DataInfoItem>,
    onClick: () -> Unit,
    onEdit: () -> Unit = {},
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(0.95f)
            .heightIn(80.dp)
            .padding(top = 16.dp, start = 16.dp, end = 16.dp),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 5.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondary
        )
    ) {
        Column(
            modifier = Modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessVeryLow
                    )
                )
        ) {
            Row(
                modifier = Modifier
                    .heightIn(80.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    Text(
                        text = title,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSecondary,
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                    )
                    Text(
                        text = description,
                        color = MaterialTheme.colorScheme.onSecondary,
                        fontSize = 14.sp,
                    )
                }

                Spacer(Modifier.weight(1f))

                IconButton(
                    onClick = { onClick() }
                ) {
                    Icon(
                        imageVector = if (open) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                        contentDescription = "Mas informacion",
                        tint = MaterialTheme.colorScheme.onSecondary
                    )
                }
            }
            if(open){
                ProfileInfo(
                    list = items
                ) {
                    onEdit()
                }
            }

        }
    }
}

@Composable
fun ProfileInfo(
    list: List<DataInfoItem>,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .fillMaxWidth()
    ) {
        list.forEach() { item ->
            InfoItem(
                title = item.title,
                inf = item.inf
            )
        }

        TextButton(
            onClick = { onClick() },
            modifier = Modifier
                .align(Alignment.End)
                .padding(16.dp)
        ) {
            Text(
                text = "EDITAR",
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
        }
    }
}

@Composable
fun InfoItem(
    title: String,
    inf: String
) {
    Row(
        modifier = Modifier
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            modifier = Modifier
                .padding(end = 16.dp)
        )
        Text(
            text = inf,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            fontSize = 14.sp,
        )
    }
}

@Composable
fun HeaderProfile(navController: NavController) {
    Column(
        modifier = Modifier
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color(0xFFE8CF41), Color(0xFFE8AA42))
                ),
                RoundedCornerShape(bottomEnd = 15.dp, bottomStart = 15.dp)
            )
            .fillMaxWidth()
            .heightIn(200.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TopBarBody(navController)

        Box(

        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = null,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(150.dp),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .align(Alignment.BottomEnd),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.End
            ) {
                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .size(48.dp)
                        .background(Color.Black, CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Add,
                        contentDescription = null,
                        modifier = Modifier
                            .size(36.dp),
                        tint = Color.White
                    )
                }
            }
        }
        Text(
            text = "Nombre de usuario",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(16.dp)
        )
    }
}

@Composable
private fun TopBarBody(navController: NavController) {
    Row(
        modifier = Modifier
            .height(64.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = { navController.navigate(AppScreens.MainScreen.route) }
        ) {
            Icon(
                imageVector = Icons.Outlined.ArrowBack,
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
            )
        }
    }
}