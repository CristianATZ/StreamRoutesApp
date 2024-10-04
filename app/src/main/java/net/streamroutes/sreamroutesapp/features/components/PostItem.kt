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
import androidx.compose.material.icons.outlined.MoreHoriz
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import net.streamroutes.sreamroutesapp.utils.TextUtils.convertTextToOrange
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
    onLikePressed: () -> Unit = {},
    onCommentPressed: () -> Unit = {}
) {
    
    // esconder dependiendo si es guardado o no

    var isExpanded by remember { mutableStateOf(false) } // Solo se necesita este estado

    Column(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
            .background(colorScheme.surfaceContainerLow)
            .clickable { onCommentPressed() }
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
                    text = post.publicationDate.toString(), // Acceso directo
                    style = typography.labelSmall,
                    modifier = Modifier.graphicsLayer(alpha = 0.5f)
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Outlined.MoreHoriz,
                    contentDescription = stringResource(id = R.string.iconMorePost)
                )
            }
        }

        // descripción
        Row(
            modifier = Modifier
                .clickable { isExpanded = !isExpanded }
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
                    text = convertTextToOrange(post.description.take(150)), // Limitar la descripción y añadir "..."
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
            Text(
                text = "${post.likes} ${stringResource(id = R.string.lblLikes)}", // Acceso directo
                style = typography.labelSmall,
                modifier = Modifier.graphicsLayer(alpha = 0.5f)
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "${post.comments.size} ${stringResource(id = R.string.lblComments)}", // Acceso directo
                style = typography.labelSmall,
                modifier = Modifier.graphicsLayer(alpha = 0.5f)
            )
        }

        Spacer(modifier = Modifier.size(8.dp))

        // botón de me gusta y comentario
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
                onClick = onCommentPressed,
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
