package net.streamroutes.sreamroutesapp.features.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.MoreHoriz
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.core.domain.model.Post
import net.streamroutes.sreamroutesapp.utils.DateUtils.fullDateFormat
import net.streamroutes.sreamroutesapp.utils.TextUtils.viewMoreTextOverflow
import net.streamroutes.sreamroutesapp.utils.formatPostDateTime
import java.time.LocalDateTime

@Preview(showBackground = true)
@Composable
fun PostItem(
    post: Post = Post(
        postId = "000",
        authorName = "CristianToZa",
        publicationDate = LocalDateTime.now(),
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
        likes = 512,
        comments = emptyList()
    ),
    isSaved: Boolean = false,
    onLikePressed: () -> Unit = {},
    onCommentPressed: () -> Unit = {},
    onDeletePost: () -> Unit = {},
    onMorePressed: () -> Unit = {}
) {
    var isExpanded by remember { mutableStateOf(false) } // Solo se necesita este estado

    val publicationDate = if(isSaved) {
        fullDateFormat(postDateTime = post.publicationDate)
    } else {
        formatPostDateTime(postDateTime = post.publicationDate)
    }

    // checar cual icono dependiendo si es guardado o no
    val icon = @Composable {
        if(isSaved) {
            Icon(
                imageVector = Icons.Outlined.Delete,
                contentDescription = stringResource(id = R.string.iconDeletePost)
            )
        } else {
            Icon(
                imageVector = Icons.Outlined.MoreHoriz,
                contentDescription = stringResource(id = R.string.iconMorePost)
            )
        }
    }

    val onIconClicked = {
        if(isSaved) {
            onDeletePost()
        } else {
            onMorePressed()
        }
    }

    val descriptionModifier = if(post.description.length <= 150) {
        Modifier
    } else {
        Modifier.clickable { isExpanded = !isExpanded }
    }

    Column(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
            .background(colorScheme.surfaceContainer)
            .clickable {
                // ABRIR BOTTOM SHEET PARA COMENTAR
                onCommentPressed()
            }
    ) {
        Spacer(modifier = Modifier.size(16.dp))

        // usuario y fecha de publicación
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = post.authorName, // Acceso directo
                    style = typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = publicationDate,
                    style = typography.labelSmall,
                    modifier = Modifier.graphicsLayer(alpha = 0.5f)
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            IconButton(onClick = onIconClicked) {
                icon()
            }
        }

        // descripción
        Row(
            modifier = descriptionModifier
        ) {
            // Comprobar si la descripción es más corta que 150 caracteres
            if (post.description.length <= 150 || isExpanded) {
                Text(
                    text = post.description,
                    style = typography.bodyMedium,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier
                        .padding(horizontal = 32.dp, vertical = 16.dp)
                        .fillMaxWidth()
                )
            } else {
                Text(
                    text = viewMoreTextOverflow(post.description.take(150)), // Limitar la descripción y añadir "..."
                    style = typography.bodyMedium,
                    textAlign = TextAlign.Justify,
                    maxLines = 4, // Limitar a 4 líneas
                    modifier = Modifier
                        .padding(horizontal = 32.dp, vertical = 16.dp)
                        .fillMaxWidth()
                )
            }
        }


        // me gusta y cantidad comentarios
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if(!isSaved) {
                Text(
                    text = stringResource(id = R.string.lblLikes, post.likes), // Acceso directo
                    style = typography.labelSmall,
                    modifier = Modifier.graphicsLayer(alpha = 0.5f)
                )

                Spacer(modifier = Modifier.weight(1f))
            }

            Text(
                text = stringResource(id = R.string.lblCountComments, post.comments.size), // Acceso directo
                style = typography.labelSmall,
                modifier = Modifier.graphicsLayer(alpha = 0.5f)
            )
        }

        Spacer(modifier = Modifier.size(8.dp))

        // botón de me gusta y comentario
        if(!isSaved) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                TextButton(
                    onClick = onLikePressed,
                    shape = shapes.small,
                    modifier = Modifier.fillMaxWidth(0.5f)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.ThumbUp,
                        contentDescription = stringResource(id = R.string.iconLikePost)
                    )

                    Spacer(modifier = Modifier.size(8.dp))

                    Text(text = stringResource(id = R.string.btnLike))
                }

                TextButton(
                    onClick = {
                        // ABRIR BOTTOM SHEET PARA COMENTAR
                        onCommentPressed()
                    },
                    shape = shapes.small,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.Outlined.ChatBubbleOutline,
                        contentDescription = stringResource(id = R.string.iconCommentPost)
                    )

                    Spacer(modifier = Modifier.size(8.dp))

                    Text(text = stringResource(id = R.string.btnComment))
                }
            }
        }
    }
}