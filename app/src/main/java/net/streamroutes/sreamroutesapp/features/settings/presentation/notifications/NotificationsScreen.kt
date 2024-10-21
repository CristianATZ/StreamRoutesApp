package net.streamroutes.sreamroutesapp.features.settings.presentation.notifications

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.features.components.SwitchField
import net.streamroutes.sreamroutesapp.features.profile.components.RowField
import net.streamroutes.sreamroutesapp.features.settings.components.DialogNotificationPush
import net.streamroutes.sreamroutesapp.features.settings.components.SettingsSmallTopAppBar


enum class NotificationType {
    NUNCA, SIEMPRE
}
@Composable
fun NotificationsScreen(
    modifier: Modifier = Modifier
) {
    val onBackPressed = {
        // regresar a configuracion
    }

    val onPushPressed = {
        // acutlaizar valor en viewmodel
    }
    var notificationType by remember {
        mutableStateOf(NotificationType.NUNCA)
    }
    val onUpdateNotificationType = { type: NotificationType ->
        notificationType = type
    }
    var isOpenDialog by remember {
        mutableStateOf(false)
    }
    val openDialog = {
        isOpenDialog = !isOpenDialog
    }

    val onPriorityPressed = {
        // acutlaizar valor en viewmodel
    }
    var priorityValue by remember {
        mutableStateOf(true)
    }

    val onVibrationPressed = {
        // acutlaizar valor en viewmodel
    }
    var vibrationValue by remember {
        mutableStateOf(true)
    }

    val onAlertPressed = {
        // acutlaizar valor en viewmodel
    }
    var alertValue by remember {
        mutableStateOf(true)
    }

    if(isOpenDialog) {

        DialogNotificationPush(
            onDismiss = openDialog,
            onSelect = {
                onUpdateNotificationType(it)
                onPushPressed()
            }
        )
    }

    Scaffold(
        topBar = {
            SettingsSmallTopAppBar(
                title = stringResource(R.string.lblNotifications),
                onBackPressed = onBackPressed,
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            // notificationes push
            RowField(
                title = stringResource(R.string.lblNotificationsPush),
                description = notificationType.name,
                onClick = openDialog
            )

            // prioridad notificaciones
            SwitchField(
                headerText = stringResource(R.string.lblNotificationPriority),
                descriptionText = stringResource(R.string.lblNotificationPriorityDescription),
                value = priorityValue,
                iconTrue = Icons.Outlined.Done,
                iconFalse = Icons.Outlined.Close,
                iconDescription = stringResource(R.string.iconDoneClose),
                onValueChange = {
                    priorityValue = it
                    onPriorityPressed()
                }
            )

            // vibraciones
            SwitchField(
                headerText = stringResource(R.string.lblVibration),
                descriptionText = stringResource(R.string.lblVibrationDescription),
                value = vibrationValue,
                iconTrue = Icons.Outlined.Done,
                iconFalse = Icons.Outlined.Close,
                iconDescription = stringResource(R.string.iconDoneClose),
                onValueChange = {
                    vibrationValue = it
                    onVibrationPressed()
                }
            )

            // alerts
            SwitchField(
                headerText = stringResource(R.string.lblAlerts),
                descriptionText = stringResource(R.string.lblAlertsDescription),
                value = alertValue,
                iconTrue = Icons.Outlined.Done,
                iconFalse = Icons.Outlined.Close,
                iconDescription = stringResource(R.string.iconDoneClose),
                onValueChange = {
                    alertValue = it
                    onAlertPressed()
                }
            )
        }
    }
}
