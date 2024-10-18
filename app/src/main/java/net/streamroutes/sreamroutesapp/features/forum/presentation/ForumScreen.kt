package net.streamroutes.sreamroutesapp.features.forum.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.compose.RumappAppTheme
import kotlinx.coroutines.launch
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.core.domain.model.Comment
import net.streamroutes.sreamroutesapp.core.domain.model.Post
import net.streamroutes.sreamroutesapp.features.components.CommentModalBottomSheet
import net.streamroutes.sreamroutesapp.features.components.CustomTopAppBar
import net.streamroutes.sreamroutesapp.features.components.NavigationButton
import net.streamroutes.sreamroutesapp.features.components.PostItem
import net.streamroutes.sreamroutesapp.features.forum.components.ForumSmallTopAppBar
import net.streamroutes.sreamroutesapp.features.forum.components.MoreModalBottomSheet
import net.streamroutes.sreamroutesapp.features.forum.components.PostModalBottomSheet
import net.streamroutes.sreamroutesapp.utils.DateUtils
import java.time.LocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForumScreen(
    modifier: Modifier = Modifier
) {

    val scope = rememberCoroutineScope()

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

    val onCloseBottomSheet = {
        scope.launch {
            /*sheetState.hide()*/
        }.invokeOnCompletion {
            /*if(!sheetState.isVisible) {
                isOpen = false
            }*/
        }
    }

    val onBackPressed = {

    }

    // comentarios
    val commentSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    var isOpenCommentSheet by remember {
        mutableStateOf(false)
    }
    val openCommentBottomSheet = {
        isOpenCommentSheet = !isOpenCommentSheet
    }

    // Mas info de la publicacion
    val moreSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    var isOpenMoreSheet by remember {
        mutableStateOf(false)
    }
    var postMoreSelect by remember {
        mutableStateOf<Pair<String, LocalDateTime>?>(null)
    }
    val openMoreBottomSheet = {
        isOpenMoreSheet = !isOpenMoreSheet
    }
    val updateMoreSelect = { info: Pair<String, LocalDateTime>? ->
        postMoreSelect = info
    }
    val onSavePost = {
        // GUARDAR PUBLICACION EN ROOM
    }
    val onHidePost = {
        // OCULTAR EN FIREABSE
    }

    // hacer publicacion
    val postSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    var isOpenPostSheet by remember {
        mutableStateOf(false)
    }
    val openPostBottomSheet = {
        isOpenPostSheet = !isOpenPostSheet
    }
    val onMakePost = {

    }
    val onCloseMakePost = {

    }

    if(isOpenPostSheet) {
        PostModalBottomSheet(
            sheetState = postSheetState,
            onDismiss = openPostBottomSheet,
            onMakePost = onMakePost,
            onCloseMakePost = onCloseMakePost,
            info = Pair("Usuario", LocalDateTime.now())
        )
    }

    if(isOpenMoreSheet) {
        postMoreSelect?.let {
            MoreModalBottomSheet(
                sheetState = moreSheetState,
                onDismiss = openMoreBottomSheet,
                onSavePressed = onSavePost,
                onHidePost = onHidePost,
                info = it
            )
        }
    }

    if(isOpenCommentSheet) {
        //ELIMINAR FONDO DEL COMENTARIO
        // PROBAR DE ESTAR FORMA
        CommentModalBottomSheet(
            sheetState = commentSheetState,
            commentList = sampleComments,
            likes = samplePosts[0].likes,
            onDismiss = openCommentBottomSheet
        )
    }

    Scaffold(
        topBar = {
            ForumSmallTopAppBar(
                title = stringResource(id = R.string.lblForum),
                onBackPressed = onBackPressed
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // CAMBIAR POR PRIMERA LETRA
                    Box(
                        modifier = Modifier
                            .padding(16.dp)
                            .background(colorScheme.tertiaryContainer, shapes.extraLarge)
                            .size(40.dp),
                        contentAlignment = Alignment.Center
                    ){
                        Text(
                            text = "C",
                            style = typography.headlineSmall,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    OutlinedCard(
                        onClick = openPostBottomSheet,
                        shape = shapes.extraLarge,
                        modifier = Modifier
                            .weight(1f)
                            .height(40.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Spacer(modifier = Modifier.size(16.dp))

                            Text(
                                text = stringResource(id = R.string.lblPostSomething)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.size(16.dp))
                }
            }

            items(samplePosts) { post ->
                PostItem(
                    post = post,
                    onLikePressed = {
                        // ACTUALIZAR DATO EN FIRESTORE
                    },
                    onCommentPressed = openCommentBottomSheet,
                    onMorePressed = {
                        updateMoreSelect(Pair(post.authorName, post.publicationDate))
                        openMoreBottomSheet()
                    }
                )
            }
        }
    }
}
