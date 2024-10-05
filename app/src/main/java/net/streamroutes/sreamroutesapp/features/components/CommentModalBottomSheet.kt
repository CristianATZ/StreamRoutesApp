package net.streamroutes.sreamroutesapp.features.components

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.automirrored.outlined.NavigateNext
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.core.domain.model.Comment

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommentModalBottomSheet(
    sheetState: SheetState = rememberModalBottomSheetState(),
    onDismiss: () -> Unit = {},
    likes: Int = 0,
    isSaved: Boolean = false,
    commentList: List<Comment> = emptyList()
) {
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
            Row(
                modifier = Modifier
                    .align(Alignment.Start)
                    .height(50.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.size(16.dp))

                Text(
                    text = stringResource(id = R.string.lblLikes, likes) ,
                    style = typography.labelLarge,
                    modifier = Modifier.graphicsLayer(alpha = 0.5f)
                )

                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.NavigateNext,
                    contentDescription = stringResource(id = R.string.iconNextComments)
                )
            }

            // lista de comentarios
            LazyColumn(
                modifier = Modifier
                    .fillMaxHeight(0.5f)
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(commentList) { comment ->
                    CommentItem(
                        comment = comment
                    )
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
        HorizontalDivider(modifier = Modifier.padding(bottom = 8.dp))

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