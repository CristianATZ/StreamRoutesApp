package net.streamroutes.sreamroutesapp.features.forum.presentation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.launch
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.core.domain.model.Comment
import net.streamroutes.sreamroutesapp.core.domain.model.Post
import net.streamroutes.sreamroutesapp.core.domain.model.PostTemp
import net.streamroutes.sreamroutesapp.features.authentication.presentation.login.LoginViewModel
import net.streamroutes.sreamroutesapp.features.components.CommentModalBottomSheet
import net.streamroutes.sreamroutesapp.features.components.PostItem
import net.streamroutes.sreamroutesapp.features.forum.components.ForumSmallTopAppBar
import net.streamroutes.sreamroutesapp.features.forum.components.MoreModalBottomSheet
import net.streamroutes.sreamroutesapp.features.forum.components.PostModalBottomSheet
import net.streamroutes.sreamroutesapp.features.profile.presentation.profile.ProfileViewModel
import net.streamroutes.sreamroutesapp.utils.shimmerEffect
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import kotlin.math.log

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForumScreen(
    onBackPressed: () -> Unit,
    forumViewModel: ForumViewModel = hiltViewModel(),
    profileViewModel: ProfileViewModel
) {
    val currentUser by profileViewModel.userData.collectAsState()

    var seeComments by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {
        forumViewModel.getAllPosts()
    }

    val scope = rememberCoroutineScope()

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
    val onMakePost = { description: String ->
        val currentDateTime = LocalDateTime.now()

        val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val currentDate = currentDateTime.format(dateFormatter)

        val timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss")
        val currentTime = currentDateTime.format(timeFormatter)

        forumViewModel.createPost(
            Post(
                date = currentDate,
                hour = currentTime,
                description = description
            )
        )

        isOpenPostSheet = false
    }
    val onCloseMakePost = {
        scope.launch {
            postSheetState.hide()
        }.invokeOnCompletion {
            if(!postSheetState.isVisible) {
                isOpenPostSheet = false
            }
        }
    }

    if(isOpenPostSheet) {
        PostModalBottomSheet(
            sheetState = postSheetState,
            onDismiss = openPostBottomSheet,
            onMakePost = { description ->
                onMakePost(description)
            },
            onCloseMakePost = {
                onCloseMakePost()
            },
            info = Pair(currentUser?.username ?: "Usuario", LocalDateTime.now())
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
            onDismiss = openCommentBottomSheet,
            forumViewModel = forumViewModel
        )
        seeComments = true
    }

    Scaffold(
        topBar = {
            ForumSmallTopAppBar(
                title = stringResource(id = R.string.lblForum),
                onBackPressed = onBackPressed
            )
        }
    ) { innerPadding ->
        if(true) {
            ForumScreenContent(
                //samplePostTemps = samplePostTemps,
                openCommentBottomSheet = openCommentBottomSheet,
                openPostBottomSheet = openPostBottomSheet,
                openMoreBottomSheet = openMoreBottomSheet,
                updateMoreSelect = { info: Pair<String, LocalDateTime>? ->
                    updateMoreSelect(info)
                },
                modifier = Modifier.padding(innerPadding),
                forumViewModel = forumViewModel,
                profileViewModel = profileViewModel
            )
        } else {
            ShimmerForumScreen(
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun ShimmerForumScreen(modifier: Modifier) {
    Column(
        modifier = modifier
    ) {
        repeat(3) {
            Box(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .fillMaxWidth()
                    .background(colorScheme.surfaceContainer)
            ) {
                Column {
                    Spacer(
                        modifier = Modifier
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                            .fillMaxWidth(0.75f)
                            .height(25.dp)
                            .clip(shapes.small)
                            .shimmerEffect()
                    )

                    Spacer(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .fillMaxWidth(0.3f)
                            .height(25.dp)
                            .clip(shapes.small)
                            .shimmerEffect()
                    )

                    Spacer(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                            .height(75.dp)
                            .clip(shapes.small)
                            .shimmerEffect()
                    )

                    Row(
                        modifier = Modifier.padding(vertical = 8.dp)
                    ) {
                        Spacer(
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .weight(1f)
                                .height(25.dp)
                                .clip(shapes.small)
                                .shimmerEffect()
                        )

                        Spacer(
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .weight(1f)
                                .height(25.dp)
                                .clip(shapes.small)
                                .shimmerEffect()
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ForumScreenContent(
    //samplePostTemps: List<PostTemp>,
    openCommentBottomSheet: () -> Unit,
    openPostBottomSheet: () -> Unit,
    modifier: Modifier,
    openMoreBottomSheet: () -> Unit,
    updateMoreSelect: (Pair<String, LocalDateTime>?) -> Unit,
    forumViewModel: ForumViewModel,
    profileViewModel: ProfileViewModel
) {
    val posts by forumViewModel.posts.collectAsState()
    val currentUser by profileViewModel.userData.collectAsState()

    if(posts.isNullOrEmpty()){
        ShimmerForumScreen(modifier)
    } else {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
        ) {
            // letra y barra de busqueda
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
                            //text = "C",
                            text = currentUser?.username?.get(0).toString(),
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

            /*
            items(samplePostTemps) { post ->
                PostItem(
                    postTemp = post,
                    onLikePressed = {
                        // ACTUALIZAR DATO EN FIRESTORE
                    },
                    onCommentPressed = openCommentBottomSheet,
                    onMorePressed = {
                        updateMoreSelect(Pair(post.authorName, post.publicationDate))
                        openMoreBottomSheet()
                    }
                )
            }*/

            items(posts!!){ post ->
                PostItem(
                    post = post,
                    onLikePressed = {
                        // ACTUALIZAR DATO EN FIRESTORE
                        Log.d("vista", "se ha seleccionado una ruta")
                    },
                    onCommentPressed = {
                        //Log.d("vista", "se ha seleccionado una ruta")
                        forumViewModel.selectPost(post)
                        openCommentBottomSheet()
                    },
                    onMorePressed = {
                        val localDate = LocalDate.parse(post.post.date)
                        val localHour = LocalTime.parse(post.post.hour)
                        val localDateTime = LocalDateTime.of(localDate, localHour)
                        updateMoreSelect(Pair(post.user.username, localDateTime))
                        openMoreBottomSheet()
                    }
                )
            }
        }
    }


}
