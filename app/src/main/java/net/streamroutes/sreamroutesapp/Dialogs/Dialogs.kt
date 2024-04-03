package net.streamroutes.sreamroutesapp.Dialogs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import net.streamroutes.sreamroutesapp.viewmodel.ConfigurationViewModel
import net.streamroutes.sreamroutesapp.viewmodel.MyViewModel
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.viewmodel.Notification

// DIALOG DE NOTIFICACIONES PUSH (NOTIFICATIONS SCREEN)
// DIALOG DE NOTIFICACIONES PUSH (NOTIFICATIONS SCREEN)
// DIALOG DE NOTIFICACIONES PUSH (NOTIFICATIONS SCREEN)


@Composable
fun PushOptions(
    text: String,
    color_letra: Color,
    variable: MutableState<Boolean>
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.size(15.dp))
        Text(
            text = text, // texto
            color = color_letra,
            fontFamily = FontFamily.SansSerif,
            fontSize = 20.sp
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            // switch
            Switch(
                checked = variable.value,
                onCheckedChange = { variable.value = it },
                colors = SwitchDefaults.colors(
                    // cuando esta activo
                    checkedThumbColor = Color.White,
                    // cuando esta inactivo
                    uncheckedThumbColor = Color.White,
                    uncheckedTrackColor = Color.DarkGray,
                    uncheckedBorderColor = Color.DarkGray
                )
            )
            Spacer(modifier = Modifier.size(15.dp))
        }
    }
}

@Composable
fun ChangeCityDialog(
    onClick: () -> Unit
) {
    val options = listOf(
        "Leon",
        "Irapuato",
        "Celaya",
        "Salamanca",
        "Guanajuato",
        "Silao",
        "Acambaro",
        "San francisco del rincon",
        "Moroleon",
        "Salvatierra",
        "Uriangato"
    )

    // Variable
    val selectedOption = remember { mutableStateOf("") }

    fun onOptionSelected(option: String) {
        selectedOption.value = option
    }

    fun isOptionSelected(option: String): Boolean {
        return selectedOption.value == option
    }

    Dialog(
        onDismissRequest = { onClick() }
    ) {
        Card {
            Column {
                options.forEach { option ->
                    Row(
                        modifier = Modifier
                            .clickable {
                                onOptionSelected(option)
                                onClick()
                            },
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Spacer(modifier = Modifier.size(16.dp))

                        Text(
                            text = option,
                            style = typography.labelLarge
                        )

                        Spacer(modifier = Modifier.weight(1f))

                        RadioButton(
                            selected = isOptionSelected(option),
                            onClick = {
                                onOptionSelected(option)
                                onClick()
                            }
                        )
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecuDialogEdit(
    onClose: () -> Unit,
    myViewModel: MyViewModel
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
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // telefono actual
                Text(
                    text = "Telefono",
                    style = typography.titleSmall,
                    modifier = Modifier
                        .padding(PaddingValues(16.dp))
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                ) {
                    if(!changePhone){
                        OutlinedTextField(
                            value = "445 141 1834",
                            onValueChange = {},
                            label = {
                                Text(
                                    text = myViewModel.languageType().get(143),
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
                                        text = myViewModel.languageType().get(138),
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
                                    //textColor = MaterialTheme.colorScheme.onTertiaryContainer,
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
                                    text = myViewModel.languageType().get(115),
                                    color = MaterialTheme.colorScheme.onTertiaryContainer
                                )
                            }

                            if(sendCodePhone){
                                OutlinedTextField(
                                    value = "",
                                    onValueChange = {},
                                    label = {
                                        Text(
                                            text = myViewModel.languageType().get(110),
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
                                        //textColor = MaterialTheme.colorScheme.onTertiaryContainer,
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
                                        text = myViewModel.languageType().get(150),
                                        color = MaterialTheme.colorScheme.onTertiaryContainer
                                    )
                                }

                                if(codeCorrectPhone){
                                    OutlinedTextField(
                                        value = "",
                                        onValueChange = {},
                                        label = {
                                            Text(
                                                text = myViewModel.languageType().get(138),
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
                                            //textColor = MaterialTheme.colorScheme.onTertiaryContainer,
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
                                                text = myViewModel.languageType().get(100),
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
                                            //textColor = MaterialTheme.colorScheme.onTertiaryContainer,
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
                                        onClick = {

                                        },
                                        shape = RoundedCornerShape(16),
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = MaterialTheme.colorScheme.primary,
                                            contentColor = MaterialTheme.colorScheme.onPrimary
                                        ),
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(top = 16.dp)
                                            .height(50.dp)
                                    ) {
                                        Text(
                                            text = myViewModel.languageType().get(92),
                                            style = typography.bodyLarge
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                // cancelar cambio de telefono
                Button(
                    onClick = {
                        changePhone = !changePhone
                    },
                    shape = RoundedCornerShape(16),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.tertiary,
                        contentColor = MaterialTheme.colorScheme.onTertiary
                    ),
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .padding(vertical = 16.dp)
                        .height(50.dp)
                ) {
                    Text(
                        text = if(!changePhone) myViewModel.languageType().get(92) else myViewModel.languageType().get(94),
                        style = typography.bodyLarge
                    )
                }

                Divider(modifier = Modifier.padding(bottom = 16.dp))

                // correo actual
                Text(
                    text = "Correo",
                    style = typography.titleSmall,
                    modifier = Modifier
                        .padding(PaddingValues(16.dp))
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth(0.9f),
                    horizontalArrangement = Arrangement.Center
                ) {
                    if(!changeEmail){
                        OutlinedTextField(
                            value = "s20120154@alumnos.itsur.edu.mx",
                            onValueChange = {},
                            label = {
                                Text(
                                    text = myViewModel.languageType().get(106),
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
                                        text = myViewModel.languageType().get(137),
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
                                    //textColor = MaterialTheme.colorScheme.onTertiaryContainer,
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
                                    text = myViewModel.languageType().get(115),
                                    color = MaterialTheme.colorScheme.onTertiaryContainer
                                )
                            }

                            if(sendCodeEmail){
                                OutlinedTextField(
                                    value = "",
                                    onValueChange = {},
                                    label = {
                                        Text(
                                            text = myViewModel.languageType().get(110),
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
                                        //textColor = MaterialTheme.colorScheme.onTertiaryContainer,
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
                                        text = myViewModel.languageType().get(150),
                                        color = MaterialTheme.colorScheme.onTertiaryContainer
                                    )
                                }

                                if(codeCorrectEmail){
                                    OutlinedTextField(
                                        value = "",
                                        onValueChange = {},
                                        label = {
                                            Text(
                                                text = myViewModel.languageType().get(137),
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
                                            //textColor = MaterialTheme.colorScheme.onTertiaryContainer,
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
                                                text = myViewModel.languageType().get(99),
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
                                            //textColor = MaterialTheme.colorScheme.onTertiaryContainer,
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
                                        onClick = {

                                        },
                                        shape = RoundedCornerShape(16),
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = MaterialTheme.colorScheme.primary,
                                            contentColor = MaterialTheme.colorScheme.onPrimary
                                        ),
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(top = 16.dp)
                                            .height(50.dp)
                                    ) {
                                        Text(
                                            text = myViewModel.languageType().get(91),
                                            style = typography.bodyLarge
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                // cambiar correo
                Button(
                    onClick = {
                        changeEmail = !changeEmail
                    },
                    shape = RoundedCornerShape(16),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.tertiary,
                        contentColor = MaterialTheme.colorScheme.onTertiary
                    ),
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .padding(vertical = 16.dp)
                        .height(50.dp)
                ) {
                    Text(
                        text = if(!changeEmail) myViewModel.languageType().get(91) else myViewModel.languageType().get(94),
                        style = typography.bodyLarge
                    )
                }

                Divider(modifier = Modifier.padding(bottom = 16.dp))



                // cambiar la contrasenia

                Text(
                    text = "Contraseña",
                    style = typography.titleSmall,
                    modifier = Modifier
                        .padding(PaddingValues(16.dp))
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth(0.9f),
                    horizontalArrangement = Arrangement.Center
                ) {
                    if(changePass){
                        Column {
                            OutlinedTextField(
                                value = "",
                                onValueChange = {},
                                label = {
                                    Text(
                                        text = myViewModel.languageType().get(101),
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
                                    //textColor = MaterialTheme.colorScheme.onTertiaryContainer,
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
                                    text = myViewModel.languageType().get(149),
                                    color = MaterialTheme.colorScheme.onTertiaryContainer
                                )
                            }

                            if(checkPass){
                                OutlinedTextField(
                                    value = "",
                                    onValueChange = {},
                                    label = {
                                        Text(
                                            text = myViewModel.languageType().get(101),
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
                                        //textColor = MaterialTheme.colorScheme.onTertiaryContainer,
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
                                            text = myViewModel.languageType().get(98),
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
                                        //textColor = MaterialTheme.colorScheme.onTertiaryContainer,
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

                                Button(
                                    onClick = {

                                    },
                                    shape = RoundedCornerShape(16),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = MaterialTheme.colorScheme.primary,
                                        contentColor = MaterialTheme.colorScheme.onPrimary
                                    ),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 16.dp)
                                        .height(50.dp)
                                ) {
                                    Text(
                                        text = myViewModel.languageType().get(90),
                                        style = typography.bodyLarge
                                    )
                                }

                            }
                        }
                    }
                }

                // cambiar contrasenia
                Button(
                    onClick = {
                        changePass = !changePass
                    },
                    shape = RoundedCornerShape(16),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.tertiary,
                        contentColor = MaterialTheme.colorScheme.onTertiary
                    ),
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .padding(top = 16.dp)
                        .height(50.dp)
                ) {
                    Text(
                        text = if(changePass) myViewModel.languageType().get(94) else myViewModel.languageType().get(90),
                        style = typography.bodyLarge
                    )
                }

                Spacer(modifier = Modifier.size(16.dp))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MembDialogEdit(
    onClose: () -> Unit,
    myViewModel: MyViewModel
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
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
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
                                text = myViewModel.languageType().get(124),
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
                        text = myViewModel.languageType().get(117),
                        color = MaterialTheme.colorScheme.onTertiaryContainer
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    AssistChip(
                        onClick = { },
                        label = {
                            Text(
                                text = myViewModel.languageType().get(87),
                                color = MaterialTheme.colorScheme.onTertiary
                            )
                        },
                        colors = AssistChipDefaults.assistChipColors(
                            containerColor = MaterialTheme.colorScheme.tertiary,
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
                                text = myViewModel.languageType().get(147),
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
                                text = myViewModel.languageType().get(126),
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
                                text = myViewModel.languageType().get(120),
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
                        value = myViewModel.languageType().get(130),
                        onValueChange = {},
                        label = {
                            Text(
                                text = myViewModel.languageType().get(144),
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
                        value = myViewModel.languageType().get(133),
                        onValueChange = {},
                        label = {
                            Text(
                                text = myViewModel.languageType().get(132),
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
                            text = myViewModel.languageType().get(128),
                            color = MaterialTheme.colorScheme.onTertiaryContainer
                        )
                    }
                }

                // cancelar suscripcion
                Button(
                    onClick = {
                        onClose()
                    },
                    shape = RoundedCornerShape(16),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.tertiary,
                        contentColor = MaterialTheme.colorScheme.onTertiary
                    ),
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .padding(top = 16.dp)
                        .height(50.dp)
                ) {
                    Text(
                        text = myViewModel.languageType().get(93),
                        style = typography.bodyLarge
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDialogEdit(
    onClose: () -> Unit,
    myViewModel: MyViewModel
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
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
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
                                text = myViewModel.languageType().get(136),
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
                            //textColor = MaterialTheme.colorScheme.onTertiaryContainer,
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
                                text = myViewModel.languageType().get(88),
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
                            //textColor = MaterialTheme.colorScheme.onTertiaryContainer,
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
                                text = myViewModel.languageType().get(105),
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
                            //textColor = MaterialTheme.colorScheme.onTertiaryContainer,
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
                                text = myViewModel.languageType().get(140),
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
                            //textColor = MaterialTheme.colorScheme.onTertiaryContainer,
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
                                text = myViewModel.languageType().get(116),
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
                            //textColor = MaterialTheme.colorScheme.onTertiaryContainer,
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
                                text = myViewModel.languageType().get(111),
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
                            //textColor = MaterialTheme.colorScheme.onTertiaryContainer,
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
                            //textColor = MaterialTheme.colorScheme.onTertiaryContainer,
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
                                text = myViewModel.languageType().get(97),
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
                            //textColor = MaterialTheme.colorScheme.onTertiaryContainer,
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
                                text = myViewModel.languageType().get(108),
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
                            //textColor = MaterialTheme.colorScheme.onTertiaryContainer,
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
                                text = myViewModel.languageType().get(109),
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
                            //textColor = MaterialTheme.colorScheme.onTertiaryContainer,
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
                                text = myViewModel.languageType().get(123),
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
                                text = myViewModel.languageType().get(139),
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
                            //textColor = MaterialTheme.colorScheme.onTertiaryContainer,
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
                Button(
                    onClick = {
                        onClose()
                    },
                    shape = RoundedCornerShape(16),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.tertiary,
                        contentColor = MaterialTheme.colorScheme.onTertiary
                    ),
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .padding(top = 16.dp)
                        .height(50.dp)
                ) {
                    Text(
                        text = myViewModel.languageType().get(122),
                        style = typography.bodyLarge
                    )
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
fun DialogPush(
    configurationViewModel: ConfigurationViewModel,
    onDismiss: () -> Unit
) {

    var siempre by remember {
        mutableStateOf(false)
    }

    var nunca by remember {
        mutableStateOf(false)
    }


    Dialog(
        onDismissRequest = { onDismiss() }
    ) {
        Card {
            Column(
                modifier = Modifier
                    .padding(PaddingValues(16.dp)),
            ) {
                // siempre
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(id = R.string.lblSiempre)
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    RadioButton(
                        selected = configurationViewModel.notificationType.equals(Notification.SIEMPRE),
                        onClick = {
                            configurationViewModel.updateNotificationType(Notification.SIEMPRE)
                        }
                    )
                }
                // configurationViewModel.notificationType.equals(Notification.SIEMPRE)

                // nunca
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(id = R.string.lblNunca)
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    RadioButton(
                        selected = configurationViewModel.notificationType.equals(Notification.NUNCA),
                        onClick = {
                            configurationViewModel.updateNotificationType(Notification.NUNCA)
                        }
                    )
                }
            }
        }
    }
}