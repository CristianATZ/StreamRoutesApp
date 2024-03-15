package net.streamroutes.sreamroutesapp.Screens.MenuScreens

import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.EmojiEmotions
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.outlined.AttachFile
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import net.streamroutes.sreamroutesapp.MyViewModel
import net.streamroutes.sreamroutesapp.navigation.AppScreens
import net.streamroutes.sreamroutesapp.R
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

data class Messages(
    val autor: Int,
    val name: String,
    val mensaje: String
)

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ChatScreen(
    navController: NavController,
    myViewModel: MyViewModel
) {
    val mensajes = listOf(
        Messages(
            autor = 2,
            name = "Nayeli",
            mensaje = "Hola, ¿Alguien puede ayudarme?"
        ),
        Messages(
            autor = 1,
            name = "Alan",
            mensaje = "Holaa amiga, ¿En que necesitas ayuda?"
        ),
        Messages(
            autor = 1,
            name = "Anaid",
            mensaje = "¡Claro que si! ¿Que necesitas?"
        ),
        Messages(
            autor = 2,
            name = "Nayeli",
            mensaje = "Necesito llegar al callejon del beso pero, no soy de aqui :("
        ),
        Messages(
            autor = 1,
            name = "Alan",
            mensaje = "¿Que ves cerca?"
        ),
        Messages(
            autor = 2,
            name = "Nayeli",
            mensaje = "Estoy en la universidad de Guanajuato"
        ),
        Messages(
            autor = 1,
            name = "Anaid",
            mensaje = "Dirigete una cuadra abajo, para esperar la ruta 5, el autobus tiene franjas azules y tiene en letras grandes RUTA 5"
        ),
        Messages(
            autor = 2,
            name = "Nayeli",
            mensaje = "Muchas gracias Anaid, tambien a ti Alan."
        )
    )

    Scaffold(
        topBar = {
            TopBarBody(navController, myViewModel)
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
                    mensajes.forEach { item ->
                        MessageIn(item)
                    }
                    Spacer(modifier = Modifier.size(75.dp))
                }
            }

            BottomBarBody(navController = navController, myViewModel)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MessageIn(
    item: Messages
) {



    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(PaddingValues(8.dp)),
        horizontalArrangement = if (item.autor == 1) Arrangement.Start else Arrangement.End
    ) {
        if (item.autor == 1){
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
                text = item.name,
                style = typography.labelMedium,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier
                    .padding(PaddingValues(8.dp))
            )

            Row {
                Text(
                    text = item.mensaje,
                    style = typography.bodyLarge,
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                )
            }

            Row {
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = DateTimeFormatter.ofPattern("hh:mm a").format(LocalDateTime.now().atZone(
                        ZoneId.of("America/Mazatlan"))),
                    style = typography.labelSmall,
                    modifier = Modifier
                        .padding(PaddingValues(8.dp))
                )
            }
        }

        if (item.autor == 0){
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
private fun TopBarBody(
    navController: NavController,
    myViewModel: MyViewModel) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = myViewModel.languageType()[356]
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
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = colorScheme.primary,
            titleContentColor = colorScheme.onPrimary,
            navigationIconContentColor = colorScheme.onPrimary
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BottomBarBody(
    navController: NavController,
    myViewModel: MyViewModel
) {
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
                    Text(
                        text = myViewModel.languageType()[357],
                        color = colorScheme.onPrimary
                    )
                },
                leadingIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Filled.EmojiEmotions,
                            contentDescription = "Emojis del chat",
                            tint = colorScheme.onPrimary
                        )
                    }
                },
                trailingIcon = {
                    Row {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Outlined.AttachFile,
                                contentDescription = "Agregar archivos",
                                tint = colorScheme.onPrimary
                            )
                        }

                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Filled.CameraAlt,
                                contentDescription = "Tomar foto",
                                tint = colorScheme.onPrimary
                            )
                        }

                        IconButton(onClick = {
                            if(mensaje.isNotEmpty()) mensaje = ""
                        }) {
                            Icon(
                                imageVector = if(mensaje.isEmpty()) Icons.Filled.Mic else Icons.Filled.Send,
                                contentDescription = "Grabar audio",
                                tint = colorScheme.onPrimary
                            )
                        }
                    }
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = colorScheme.primary,
                    unfocusedContainerColor = colorScheme.primary,
                    focusedTextColor = colorScheme.onPrimary,
                    unfocusedTextColor = colorScheme.onPrimary
                ),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
        }
    }
}