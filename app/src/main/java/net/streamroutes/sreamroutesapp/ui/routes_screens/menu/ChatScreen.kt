package net.streamroutes.sreamroutesapp.ui.routes_screens.menu

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
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material.icons.outlined.AttachFile
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import net.streamroutes.sreamroutesapp.utils.MyViewModel
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.viewmodel.routes.ChatViewModel
import net.streamroutes.sreamroutesapp.viewmodel.routes.Mensaje
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ChatScreen(
    chatViewModel: ChatViewModel = ChatViewModel(),
    onBack: () -> Unit
) {
    val mensajes = chatViewModel.messages

    Scaffold(
        topBar = {
            TopBar(onBack)
        },
    ) { paddingValues ->
        Box {
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
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

            BottomBarBody()
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MessageIn(
    item: Mensaje
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(PaddingValues(8.dp)),
        horizontalArrangement = if (item.autor == 1) Arrangement.Start else Arrangement.End
    ) {
        if (item.autor == 1){
            Image(
                painter = painterResource(id = item.imagen),
                contentDescription = "Avatar del usuario",
                modifier = Modifier
                    .clip(CircleShape)
                    .size(50.dp)
            )
            Spacer(modifier = Modifier.size(8.dp))
        }

        Card(
            colors = CardDefaults.cardColors(
                containerColor =
                    if(item.autor == 2) colorScheme.onTertiaryContainer.copy(0.2f) else colorScheme.onPrimaryContainer.copy(0.3f)
            ),
            modifier = Modifier
                .fillMaxWidth(0.6f)
        ) {
            Text(
                text = item.usuario,
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
                    // text = DateTimeFormatter.ofPattern("hh:mm a").format(LocalDateTime.now().atZone(
                    //    ZoneId.of("America/Mazatlan"))),
                    text = item.hora,
                    style = typography.labelSmall,
                    modifier = Modifier
                        .padding(PaddingValues(8.dp))
                )
            }
        }

        if (item.autor == 0){
            Spacer(modifier = Modifier.size(8.dp))
            Image(
                painter = painterResource(item.imagen),
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
private fun TopBar(
    onBack: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.lblChat)
            )
        },
        navigationIcon = {
            IconButton(onClick = { onBack() } ) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBackIosNew,
                    contentDescription = "Te enviara al menu de opciones",
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = colorScheme.background,
            titleContentColor = colorScheme.onBackground
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BottomBarBody() {
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
                        text = stringResource(id = R.string.contact_head_message),
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
                    focusedContainerColor = colorScheme.inverseSurface,
                    focusedTextColor = colorScheme.inverseOnSurface,
                    focusedBorderColor = colorScheme.inverseSurface,
                    unfocusedContainerColor = colorScheme.inverseSurface,
                    unfocusedTextColor = colorScheme.inverseOnSurface,
                    unfocusedBorderColor = colorScheme.inverseSurface,
                ),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
        }
    }
}