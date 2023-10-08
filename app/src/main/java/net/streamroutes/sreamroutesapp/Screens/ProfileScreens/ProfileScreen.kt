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
import net.streamroutes.sreamroutesapp.MyViewModel
import net.streamroutes.sreamroutesapp.Navigation.AppScreens
import net.streamroutes.sreamroutesapp.R

data class DataInfoItem(val title: String, val inf: String)

@Composable
fun ProfileScreen(
    navController: NavController,
    myViewModel: MyViewModel
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
            HeaderProfile(navController,myViewModel)

            FeaturedProfile(myViewModel)
        }
    }
}

@Composable
fun FooterProfile(
 myViewModel: MyViewModel
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
                DataInfoItem(myViewModel.languageType().get(109),"Mie, 27 Nov 2002"),
                DataInfoItem(myViewModel.languageType().get(143),"445 141 1834"),
                DataInfoItem(myViewModel.languageType().get(140),"Mexico"),
                DataInfoItem(myViewModel.languageType().get(96),"Moroleon, Gto")
            )
        )
    }

    var secInfo by remember {
        mutableStateOf(
            listOf(
                DataInfoItem(myViewModel.languageType().get(103),myViewModel.languageType().get(151)),
                DataInfoItem(myViewModel.languageType().get(148), myViewModel.languageType().get(86)),
                DataInfoItem(myViewModel.languageType().get(106), myViewModel.languageType().get(89)),
                DataInfoItem(myViewModel.languageType().get(143), myViewModel.languageType().get(89))
            )
        )
    }

    var memberInfo by remember {
        mutableStateOf(
            listOf(
                DataInfoItem(myViewModel.languageType().get(145),myViewModel.languageType().get(119)),
                DataInfoItem(myViewModel.languageType().get(112),myViewModel.languageType().get(131)),
                DataInfoItem(myViewModel.languageType().get(107),"Vie, 29 Sep 2023"),
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
                onClose = { openUse = !openUse },
                myViewModel = myViewModel
            )
        }

        if(openMem){
            MembDialogEdit(
                onClose = { openMem = !openMem },
                myViewModel = myViewModel
            )
        }

        if(openSec){
            SecuDialogEdit(
                onClose = { openSec = !openSec },
                myViewModel = myViewModel
            )
        }

        ProfileItems(
            title = myViewModel.languageType().get(125),
            description = myViewModel.languageType().get(121),
            open = user,
            items = userInfo,
            onClick = {
                user = !user
            },
            onEdit = { openUse = !openUse },
            myViewModel = myViewModel
        )

        Spacer(modifier = Modifier.size(8.dp))

        ProfileItems(
            title = myViewModel.languageType().get(129),
            description = myViewModel.languageType().get(126),
            open = member,
            items = memberInfo,
            onClick = { member = !member },
            onEdit = { openMem = !openMem },
            myViewModel = myViewModel
        )

        Spacer(modifier = Modifier.size(8.dp))

        ProfileItems(
            title = myViewModel.languageType().get(142),
            description = myViewModel.languageType().get(104),
            open = seguridad,
            items = secInfo,
            onClick = { seguridad = !seguridad },
            onEdit = { openSec = !openSec },
            myViewModel = myViewModel
        )

        Spacer(modifier = Modifier.size(16.dp))
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
                                            text = myViewModel.languageType().get(92),
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
                            text = if(!changePhone) myViewModel.languageType().get(92) else myViewModel.languageType().get(94),
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
                                            text = myViewModel.languageType().get(91),
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
                            text = if(!changeEmail) myViewModel.languageType().get(91) else myViewModel.languageType().get(94),
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
                                        text = myViewModel.languageType().get(90),
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
                            text = if(changePass) myViewModel.languageType().get(94) else myViewModel.languageType().get(90),
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
                            text = myViewModel.languageType().get(93),
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
                            text = myViewModel.languageType().get(122),
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
fun FeaturedProfile(myViewModel: MyViewModel) {
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
            FeaturedInformation(inf = "0", title = myViewModel.languageType().get(141))

            FeaturedInformation(
                inf = myViewModel.languageType().get(118),
                title = myViewModel.languageType().get(128)
            )

            FeaturedInformation(icon = Icons.Outlined.Check,title = myViewModel.languageType().get(148))
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            FeaturedInformation(inf = myViewModel.languageType().get(114),title = myViewModel.languageType().get(127), large = true)

            FeaturedInformation(icon = Icons.Outlined.ExitToApp,title = myViewModel.languageType().get(95))
        }

        FooterProfile(myViewModel)

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
    myViewModel: MyViewModel
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
                        contentDescription = myViewModel.languageType().get(134),
                        tint = MaterialTheme.colorScheme.onSecondary
                    )
                }
            }
            if(open){
                ProfileInfo(
                    list = items,
                    myViewModel = myViewModel,
                    onClick = { onEdit() }
                )
            }

        }
    }
}

@Composable
fun ProfileInfo(
    list: List<DataInfoItem>,
    onClick: () -> Unit,
    myViewModel: MyViewModel
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
                text = myViewModel.languageType().get(113),
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
fun HeaderProfile(navController: NavController, myViewModel: MyViewModel) {
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
            text = myViewModel.languageType().get(135),
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