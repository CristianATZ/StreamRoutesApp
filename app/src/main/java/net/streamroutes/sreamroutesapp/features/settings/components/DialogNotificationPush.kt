package net.streamroutes.sreamroutesapp.features.settings.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.features.settings.presentation.notifications.NotificationType

@Composable
fun DialogNotificationPush(
    onDismiss: () -> Unit,
    optionSelected: NotificationType,
    onSelect: (NotificationType) -> Unit
) {
    var option by remember {
        mutableStateOf(optionSelected)
    }

    val radioPressed = {
        option = when(option) {
            NotificationType.NUNCA -> NotificationType.SIEMPRE
            else -> NotificationType.NUNCA
        }
    }

    Dialog(
        onDismissRequest = onDismiss
    ) {
        Card {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.lblNotificationsPush),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 16.dp)
                )

                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 16.dp)
                )

                RadioField(
                    text = stringResource(R.string.lblEver),
                    selected = option == NotificationType.SIEMPRE,
                    onSelect = radioPressed
                )

                Spacer(modifier = Modifier.size(8.dp))

                RadioField(
                    text = stringResource(R.string.lblNever),
                    selected = option == NotificationType.NUNCA,
                    onSelect = radioPressed
                )

                Spacer(modifier = Modifier.size(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(
                        onClick = onDismiss
                    ) {
                        Text(text = stringResource(R.string.btnCancel))
                    }

                    TextButton(
                        onClick = {
                            onSelect(option)
                            onDismiss()
                        }
                    ) {
                        Text(text = stringResource(R.string.btnAccept))
                    }
                }
            }
        }
    }
}