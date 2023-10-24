package net.streamroutes.sreamroutesapp.Screens.MenuScreens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.EmojiEmotions
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.outlined.AttachFile
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.android.filament.Box
import net.streamroutes.sreamroutesapp.Navigation.AppScreens
import net.streamroutes.sreamroutesapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    navController: NavController
) {
    Scaffold(
        topBar = {
            TopBarBody(navController)
        },
    ) { paddingValues ->
        Box {
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .background(colorScheme.inverseOnSurface)
            ) {
                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                ) {
                    for (i in 1..5){
                        MessageIn(1)
                        MessageIn(autor = 0)
                    }

                    Spacer(modifier = Modifier.size(75.dp))
                }
            }

            BottomBarBody(navController = navController)
        }
    }
}

@Composable
fun MessageIn(
    autor: Int
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(PaddingValues(8.dp)),
        horizontalArrangement = if (autor == 1) Arrangement.Start else Arrangement.End
    ) {
        if (autor == 1){
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "Avatar del usuario",
                modifier = Modifier
                    .clip(CircleShape)
                    .size(50.dp)
            )
            Spacer(modifier = Modifier.size(8.dp))
        }

        Card(
            modifier = Modifier
                .fillMaxWidth(0.6f)
        ) {
            Text(
                text = "Ernesto",
                style = typography.labelSmall,
                modifier = Modifier
                    .padding(PaddingValues(8.dp))
            )

            Row {
                Text(
                    text = "Hola, como estas, necesitas ayuda?",
                    style = typography.bodyLarge,
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                )
            }

            Row {
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "12:34 p. m.",
                    style = typography.labelSmall,
                    modifier = Modifier
                        .padding(PaddingValues(8.dp))
                )
            }
        }

        if (autor == 0){
            Spacer(modifier = Modifier.size(8.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "Avatar del usuario",
                modifier = Modifier
                    .clip(CircleShape)
                    .size(50.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBarBody(navController: NavController) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "Chat general"
            )
        },
        navigationIcon = {
            IconButton(onClick = { navController.navigate(AppScreens.MainScreen.route) }) {
                Icon(
                    imageVector = Icons.Outlined.KeyboardArrowLeft,
                    contentDescription = "Te enviara al menu de opciones",
                )
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = colorScheme.primary,
            titleContentColor = colorScheme.onPrimary,
            navigationIconContentColor = colorScheme.onPrimary
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BottomBarBody(navController: NavController) {
    var mensaje by remember {
        mutableStateOf("")
    }

    Column {
        Spacer(modifier = Modifier.weight(1f))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = mensaje,
                onValueChange = {mensaje = it},
                placeholder = {
                    Text(text = "Mensaje")
                },
                leadingIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Filled.EmojiEmotions,
                            contentDescription = "Emojis del chat"
                        )
                    }
                },
                trailingIcon = {
                    Row {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Outlined.AttachFile,
                                contentDescription = "Agregar archivos"
                            )
                        }

                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Filled.CameraAlt,
                                contentDescription = "Tomar foto"
                            )
                        }

                        IconButton(onClick = {
                            if(mensaje.isNotEmpty()) mensaje = ""
                        }) {
                            Icon(
                                imageVector = if(mensaje.isEmpty()) Icons.Filled.Mic else Icons.Filled.Send,
                                contentDescription = "Grabar audio"
                            )
                        }
                    }
                },
                shape = RoundedCornerShape(16.dp),
                /*colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = colorScheme.tertiary,
                    textColor = colorScheme.onTertiary,
                    cursorColor = colorScheme.onTertiary,
                    focusedBorderColor = colorScheme.onTertiary,
                    unfocusedBorderColor = colorScheme.tertiary,
                    focusedLeadingIconColor = colorScheme.onTertiary,
                    focusedTrailingIconColor = colorScheme.onTertiary,
                    unfocusedLeadingIconColor = colorScheme.onTertiary,
                    unfocusedTrailingIconColor = colorScheme.onTertiary,
                    unfocusedLabelColor = colorScheme.onTertiary,
                ),*/
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
        }
    }
}