package net.streamroutes.sreamroutesapp.features.profile.presentation.posts

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.RumappAppTheme
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.core.domain.model.Comment
import net.streamroutes.sreamroutesapp.core.domain.model.Post
import net.streamroutes.sreamroutesapp.features.components.CommentModalBottomSheet
import net.streamroutes.sreamroutesapp.features.components.PostItem
import net.streamroutes.sreamroutesapp.features.profile.components.ProfileSmallTopAppBar
import java.time.LocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun SavedPostScreen(
    modifier: Modifier = Modifier
) {
    // no pasar el modifier, solo en caso de que no se coloree
    // si no se colorea, usar scaffold para encapsular las cosas


    // ELIMINAR ESTAS LISTAS
    val samplePosts = listOf(
        Post(
            postId = "001",
            authorName = "CristianToZa",
            publicationDate = LocalDateTime.of(2023, 10, 1, 12, 0),
            description = "¡Hola a todos! Estoy emocionado de compartir mi primer proyecto en el foro. Espero que les guste.",
            likes = 25,
            comments = listOf(
                Comment("Usuario1", LocalDateTime.of(2023, 10, 1, 12, 30), "¡Gran trabajo, Cristian!"),
                Comment("Usuario2", LocalDateTime.of(2023, 10, 1, 13, 0), "Estoy ansioso por verlo.")
            )
        ),
        Post(
            postId = "002",
            authorName = "DeveloperX",
            publicationDate = LocalDateTime.of(2023, 10, 2, 14, 0),
            description = "¿Alguien más ha tenido problemas con el último SDK de Android?",
            likes = 12,
            comments = listOf(
                Comment("Usuario3", LocalDateTime.of(2023, 10, 2, 15, 0), "Sí, yo también tengo algunos errores."),
                Comment("CristianToZa", LocalDateTime.of(2023, 10, 2, 15, 30), "Intenté reinstalarlo y funcionó.")
            )
        ),
        Post(
            postId = "003",
            authorName = "TechEnthusiast",
            publicationDate = LocalDateTime.of(2023, 10, 3, 16, 0),
            description = "Revisé el nuevo framework de Jetpack Compose y me encantó. ¡Es muy fácil de usar!",
            likes = 30,
            comments = listOf(
                Comment("Usuario4", LocalDateTime.of(2023, 10, 3, 16, 30), "¡Totalmente de acuerdo!"),
                Comment("DeveloperX", LocalDateTime.of(2023, 10, 3, 17, 0), "Me gustaría ver más tutoriales sobre esto.")
            )
        ),
        Post(
            postId = "004",
            authorName = "CodeMaster",
            publicationDate = LocalDateTime.of(2023, 10, 4, 18, 0),
            description = "¿Alguien tiene consejos sobre cómo mejorar la optimización de una aplicación?¿Alguien tiene consejos sobre cómo mejorar la optimización de una aplicación?¿Alguien tiene consejos sobre cómo mejorar la optimización de una aplicación?",
            likes = 5,
            comments = listOf(
                Comment("CristianToZa", LocalDateTime.of(2023, 10, 4, 18, 30), "Asegúrate de usar el ViewModel correctamente."),
                Comment("Usuario5", LocalDateTime.of(2023, 10, 4, 19, 0), "Revisa la documentación de Android sobre optimización.")
            )
        ),
        Post(
            postId = "005",
            authorName = "AndroidFan",
            publicationDate = LocalDateTime.of(2023, 10, 5, 20, 0),
            description = "He estado experimentando con la biblioteca de Material Design y me gusta mucho cómo se ve.",
            likes = 20,
            comments = listOf(
                Comment("Usuario6", LocalDateTime.of(2023, 10, 5, 20, 30), "Es una excelente manera de mejorar la UI."),
                Comment("TechEnthusiast", LocalDateTime.of(2023, 10, 5, 21, 0), "¡Sí! Definitivamente le da un gran aspecto a las aplicaciones.")
            )
        )
    )

    val sampleComments = listOf(
        Comment(
            commenterName = "CristianToZa",
            description = "Hola como estas amigo",
            commentDate = LocalDateTime.of(2023, 10, 1, 12, 0),
        ),
        Comment(
            commenterName = "CristianToZa",
            description = "Hola como estas amigo",
            commentDate = LocalDateTime.of(2023, 10, 1, 12, 0),
        ),
        Comment(
            commenterName = "CristianToZa",
            description = "Hola como estas amigo",
            commentDate = LocalDateTime.of(2023, 10, 1, 12, 0),
        ),
        Comment(
            commenterName = "CristianToZa",
            description = "Hola como estas amigo",
            commentDate = LocalDateTime.of(2023, 10, 1, 12, 0),
        ),
        Comment(
            commenterName = "CristianToZa",
            description = "Hola como estas amigo",
            commentDate = LocalDateTime.of(2023, 10, 1, 12, 0),
        ),
        Comment(
            commenterName = "CristianToZa",
            description = "Hola como estas amigo",
            commentDate = LocalDateTime.of(2024, 6, 1, 12, 0),
        ),
        Comment(
            commenterName = "CristianToZa",
            description = "Hola como estas amigo",
            commentDate = LocalDateTime.of(2023, 2, 1, 12, 0),
        ),
        Comment(
            commenterName = "CristianToZa",
            description = "Hola como estas amigo",
            commentDate = LocalDateTime.of(2023, 9, 1, 12, 0),
        ),
        Comment(
            commenterName = "CristianToZa",
            description = "Hola como estas amigo",
            commentDate = LocalDateTime.of(2023, 7, 1, 12, 0),
        )
    )

    var isOpen by remember {
        mutableStateOf(false)
    }

    val openBottomSheet = {
        isOpen = !isOpen
    }

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    if(isOpen) {
        CommentModalBottomSheet(
            sheetState = sheetState,
            commentList = sampleComments,
            likes = samplePosts[0].likes,
            onDismiss = openBottomSheet,
            isSaved = true
        )
    }

    Scaffold(
        topBar = {
            ProfileSmallTopAppBar(
                title = stringResource(id = R.string.lblPosts),
                onBackPressed = {
                    // REGRESAR A EDITAR PERFIL
                }
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Text(
                    text = stringResource(id = R.string.lblPostSavedAmount, sampleComments.size),
                    style = typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .graphicsLayer(alpha = 0.5f)
                )
            }

            items(samplePosts) { post ->
                PostItem(
                    post = post,
                    isSaved = true,
                    onLikePressed = {
                        // ACTUALIZAR DATO EN FIRESTORE
                    },
                    onCommentPressed = openBottomSheet
                )
            }
        }
    }
}