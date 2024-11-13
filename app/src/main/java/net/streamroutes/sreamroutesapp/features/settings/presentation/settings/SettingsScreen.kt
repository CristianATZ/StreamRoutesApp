package net.streamroutes.sreamroutesapp.features.settings.presentation.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.features.profile.components.RowField
import net.streamroutes.sreamroutesapp.features.settings.components.SettingsSmallTopAppBar

@Composable
fun SettingsScreen(
    onBackPressed: () -> Unit,
    onNotificationPressed: () -> Unit,
    onMapsPressed: () -> Unit,
    onPrivacityPressed: () -> Unit,
    onStoragePressed: () -> Unit,
    onApparencePressed: () -> Unit
) {
    Scaffold(
        topBar = {
            SettingsSmallTopAppBar(
                title = stringResource(R.string.lblSettings),
                onBackPressed = onBackPressed,
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            // notificaciones
            RowField(
                title = stringResource(R.string.lblSettingsNotificationTitle),
                description = stringResource(R.string.lblSettingsNotificationDescription),
                onClick = onNotificationPressed,
                modifier = Modifier.fillMaxWidth()
            )
            // mapas
            RowField(
                title = stringResource(R.string.lblSettingsMapsTitle),
                description = stringResource(R.string.lblSettingsMapsDescription),
                onClick = onMapsPressed,
                modifier = Modifier.fillMaxWidth()
            )
            // privacidad
            RowField(
                title = stringResource(R.string.lblSettingsPrivacityTitle),
                description = stringResource(R.string.lblSettingsPrivacityDescription),
                onClick = onPrivacityPressed,
                modifier = Modifier.fillMaxWidth()
            )
            // almacenamiento y datos
            RowField(
                title = stringResource(R.string.lblSettingsStorageTitle),
                description = stringResource(R.string.lblSettingsStorageDescription),
                onClick = onStoragePressed,
                modifier = Modifier.fillMaxWidth()
            )
            // apariencia
            RowField(
                title = stringResource(R.string.lblSettingsApparenceTitle),
                description = stringResource(R.string.lblSettingsApparenceDescription),
                onClick = onApparencePressed,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}