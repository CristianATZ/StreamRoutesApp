package net.streamroutes.sreamroutesapp.features.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.core.domain.model.Comment
import net.streamroutes.sreamroutesapp.features.authentication.presentation.login.LoginViewModel
import net.streamroutes.sreamroutesapp.features.forum.presentation.ForumViewModel
import net.streamroutes.sreamroutesapp.features.profile.presentation.profile.ProfileViewModel
import net.streamroutes.sreamroutesapp.utils.shimmerEffect
import java.time.LocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommentModalBottomSheet(
    sheetState: SheetState = rememberModalBottomSheetState(),
    onDismiss: () -> Unit = {},
    isSaved: Boolean = false,
    profileViewModel: ProfileViewModel,
    forumViewModel: ForumViewModel
) {
    val currentUser by profileViewModel.userData.collectAsState()
    val selectPost by forumViewModel.selectedPost.collectAsState()
    val comments by forumViewModel.comments.collectAsState()

    var isLoading by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {
        Log.d("vista", selectPost.toString())
        forumViewModel.getCommentsByPost()
    }


    val sampleComments = listOf(
        Comment(
            commenterName = currentUser?.names ?: "cargando...",
            description = "¡Hola! ¿Cómo te va? Hace tiempo que no hablamos, espero que todo esté bien contigo y que las cosas estén marchando de maravilla. ¿Qué has estado haciendo últimamente?",
            commentDate = LocalDateTime.of(2023, 10, 1, 12, 0),
        ),
        Comment(
            commenterName = "CristianToZa",
            description = "Espero que tengas un buen día lleno de éxitos y alegrías. Siempre es importante recordar que cada día es una nueva oportunidad para aprender algo nuevo y crecer.",
            commentDate = LocalDateTime.of(2023, 10, 1, 12, 0),
        ),
        Comment(
            commenterName = "CristianToZa",
            description = "¿Qué planes tienes para hoy? Estoy pensando en salir a caminar un rato por el parque para despejarme y aprovechar el buen clima. Sería genial si te animas a venir también.",
            commentDate = LocalDateTime.of(2023, 10, 1, 12, 0),
        ),
        Comment(
            commenterName = "CristianToZa",
            description = "¡Nos vemos luego! Estaré por la zona del centro en la tarde, así que si tienes tiempo libre podemos juntarnos para tomar un café y ponernos al día.",
            commentDate = LocalDateTime.of(2023, 10, 1, 12, 0),
        ),
        Comment(
            commenterName = "CristianToZa",
            description = "Quería comentarte sobre ese proyecto en el que estás trabajando, he estado pensando en algunas ideas que podrían ser útiles y me encantaría compartirlas contigo pronto.",
            commentDate = LocalDateTime.of(2023, 10, 1, 12, 0),
        ),
        Comment(
            commenterName = "CristianToZa",
            description = "No puedo creer que ya estemos en junio de 2024, el tiempo vuela. Parece que fue ayer cuando comenzamos este año, y ahora ya estamos a mitad de camino.",
            commentDate = LocalDateTime.of(2024, 6, 1, 12, 0),
        ),
        Comment(
            commenterName = "CristianToZa",
            description = "El invierno de este año ha sido bastante frío, ¿no crees? Aunque a veces disfruto de los días frescos, estoy deseando que llegue la primavera para poder salir más.",
            commentDate = LocalDateTime.of(2023, 2, 1, 12, 0),
        ),
        Comment(
            commenterName = "CristianToZa",
            description = "Ya casi es septiembre, uno de mis meses favoritos. Me encanta cuando comienza el otoño, el clima es perfecto y los paisajes se ven impresionantes con los colores de las hojas.",
            commentDate = LocalDateTime.of(2023, 9, 1, 12, 0),
        ),
        Comment(
            commenterName = "HOla",
            description = "El verano ha sido bastante caluroso este año, pero he disfrutado de varias salidas a la playa. Me encanta la sensación de estar cerca del mar, es realmente relajante.",
            commentDate = LocalDateTime.of(2023, 7, 1, 12, 0),
        )
    )


    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = onDismiss,
        modifier = Modifier.fillMaxHeight(0.8f)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .navigationBarsPadding(),
            //.windowInsetsPadding(WindowInsets.navigationBars)
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // cantidad de likes e icono
            Text(
                text = stringResource(R.string.lblComments),
                fontWeight = FontWeight.Bold
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp).graphicsLayer(alpha = 0.5f))

            if(!isLoading) {
                // lista de comentarios
                LazyColumn(
                    modifier = Modifier
                        .fillMaxHeight(0.5f)
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    /*
                    items(sampleComments) { comment ->
                        CommentItem(
                            comment = comment
                        )
                    }*/
                    if(comments != null){
                        items(comments!!) { comment ->
                            CommentItem(
                                comment = comment
                            )
                        }
                    }

                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxHeight(0.5f)
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(3) {
                        Row {
                            Spacer(
                                modifier = Modifier
                                    .padding(horizontal = 8.dp)
                                    .size(50.dp)
                                    .clip(CircleShape)
                                    .shimmerEffect()
                            )

                            Column {
                                Spacer(
                                    modifier = Modifier
                                        .padding(start = 8.dp, end = 16.dp)
                                        .fillMaxWidth(0.4f)
                                        .height(25.dp)
                                        .clip(shapes.small)
                                        .shimmerEffect()
                                )

                                Spacer(
                                    modifier = Modifier
                                        .padding(start = 8.dp, end = 16.dp, top = 8.dp, bottom = 16.dp)
                                        .fillMaxWidth()
                                        .height(50.dp)
                                        .clip(shapes.small)
                                        .shimmerEffect()
                                )
                            }
                        }
                    }
                }
            }

            if(!isSaved) {
                CommentInput()
            }
        }
    }
}


@Composable
fun CommentInput() {
    // Controlador del teclado
    val keyboardController = LocalSoftwareKeyboardController.current
    var comment by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .imePadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalDivider(modifier = Modifier.padding(bottom = 8.dp).graphicsLayer(alpha = 0.5f))

        // Iconos de acción
        TextField(
            value = comment,
            onValueChange = { comment = it },
            shape = shapes.medium,
            maxLines = 3,
            placeholder = {
                Text(
                    text = stringResource(id = R.string.lblPlaceholderComment),
                    modifier = Modifier.graphicsLayer(alpha = 0.5f)
                )
            },
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .heightIn(min = 50.dp, max = 100.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(
                onClick = {
                    comment = ""
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = stringResource(id = R.string.iconDeleteComment)
                )
            }

            IconButton(
                onClick = {
                    // Acción para enviar comentario
                    // ENVIAR COMENTARIO EN FIREBASE
                    keyboardController?.hide() // Cerrar el teclado si es necesario
                }
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.Send,
                    contentDescription = stringResource(id = R.string.iconSendComment)
                )
            }
        }

        // esto sirve para enviar las cosas encima del teclado al abrirse
        Spacer(
            Modifier.windowInsetsBottomHeight(
                WindowInsets.systemBars
            )
        )
    }
}