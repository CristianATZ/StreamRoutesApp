package net.streamroutes.sreamroutesapp.features.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.streamroutes.sreamroutesapp.core.domain.model.Comment
import net.streamroutes.sreamroutesapp.utils.TextUtils.convertTextToOrange
import net.streamroutes.sreamroutesapp.utils.formatPostDateTime
import java.time.LocalDateTime

@Preview(showBackground = true)
@Composable
fun CommentItem(
    modifier: Modifier = Modifier,
    comment: Comment = Comment(commenterName = "CristianToZa", description = "Hola como estas amigo", commentDate = LocalDateTime.now())
) {
    var isExpanded by remember { mutableStateOf(false) } // Solo se necesita este estado

    val publicationDate = formatPostDateTime(postDateTime = comment.commentDate)

    Column(
        modifier = modifier
            .padding(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // autor y comentario
        Card(
            colors = CardDefaults.cardColors(
                containerColor = colorScheme.surfaceContainerHighest
            )
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .fillMaxWidth(0.9f)
            ) {
                Text(
                    text = comment.commenterName, // Acceso directo
                    style = typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )

                // Comprobar si la descripción es más corta que 150 caracteres
                if (comment.description.length <= 200 || isExpanded) {
                    Text(
                        text = comment.description,
                        style = typography.bodyMedium,
                        textAlign = TextAlign.Justify,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                } else {
                    Text(
                        text = convertTextToOrange(comment.description.take(200)), // Limitar la descripción y añadir "..."
                        style = typography.bodyMedium,
                        textAlign = TextAlign.Justify,
                        maxLines = 4, // Limitar a 4 líneas
                        modifier = Modifier
                            .clickable {
                                isExpanded = !isExpanded
                            }
                            .fillMaxWidth()
                    )
                }
            }
        }

        // fecha de comentario
        Text(
            text = publicationDate,
            style = typography.labelSmall,
            modifier = Modifier
                .padding(start = 8.dp)
                .graphicsLayer(alpha = 0.5f)
                .align(Alignment.Start)
        )
    }
}