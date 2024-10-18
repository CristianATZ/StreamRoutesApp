package net.streamroutes.sreamroutesapp.features.forum.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.utils.DateUtils
import java.time.LocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostModalBottomSheet(
    sheetState: SheetState,
    onDismiss: () -> Unit,
    onMakePost: () -> Unit,
    onCloseMakePost: () -> Unit,
    info: Pair<String, LocalDateTime>
) {
    var post by remember {
        mutableStateOf("")
    }

    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = onDismiss
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.9f)
                .navigationBarsPadding()
        ) {
            // letra, nombre y fecha
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .background(colorScheme.tertiaryContainer, shapes.extraLarge)
                        .size(40.dp),
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        text = info.first[0].toString(),
                        style = typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )
                }

                Column {
                    Text(
                        text = info.first,
                        style = typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                    Text(
                        text = DateUtils.fullDateFormat(info.second),
                        style = typography.labelMedium,
                        modifier = Modifier
                            .graphicsLayer(alpha = 0.5f)
                            .padding(start = 16.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.size(64.dp))

            // descripcion publicacion
            TextField(
                value = post,
                onValueChange = { post = it },
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.lblThinking),
                        style = typography.headlineLarge
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            Spacer(modifier = Modifier.weight(1f))

            // regresar
            OutlinedButton(
                onClick = onCloseMakePost,
                shape = shapes.small,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth()
            ) {
                Text(text = stringResource(id = R.string.btnHidePost))
            }

            // publicar
            Button(
                onClick = onMakePost,
                shape = shapes.small,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth()
            ) {
                Text(text = stringResource(id = R.string.btnSavePost))
            }
        }
    }
}