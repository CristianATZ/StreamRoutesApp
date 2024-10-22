package net.streamroutes.sreamroutesapp.features.settings.presentation.storage

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.compose.orange
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.features.components.InfoRowField
import net.streamroutes.sreamroutesapp.features.settings.components.SettingsSmallTopAppBar
import net.streamroutes.sreamroutesapp.utils.StorageUtils.formatBytes
import net.streamroutes.sreamroutesapp.utils.StorageUtils.getAppStorageUsage
import net.streamroutes.sreamroutesapp.utils.StorageUtils.getAvailableStorage
import net.streamroutes.sreamroutesapp.utils.StorageUtils.getSystemUsedStorage
import net.streamroutes.sreamroutesapp.utils.StorageUtils.getTotalStorage

@Composable
fun StorageScreen(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    val onBackPressed = {

    }

    val gbps by remember {
        mutableDoubleStateOf(0.0)
    }

    val usedStorage by remember {
        mutableLongStateOf(getSystemUsedStorage())
    }

    val availableStorage by remember {
        mutableLongStateOf(getAvailableStorage())
    }

    val totalStorage by remember {
        mutableLongStateOf(getTotalStorage())
    }

    val appStorage by remember {
        mutableLongStateOf(getAppStorageUsage(context))
    }

    Scaffold(
        topBar = {
            SettingsSmallTopAppBar(
                title = stringResource(R.string.lblSettingsStorageTitle),
                onBackPressed = onBackPressed,
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            Spacer(Modifier.size(16.dp))

            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = stringResource(R.string.lblRedUse),
                    style = typography.labelLarge
                )

                Text(
                    text = gbps.toString(),
                    style = typography.displayLarge,
                    modifier = Modifier.graphicsLayer(alpha = 0.5f)
                )
            }

            HorizontalDivider(modifier = Modifier
                .graphicsLayer(alpha = 0.5f)
                .padding(top = 16.dp, bottom = 8.dp))

            InfoRowField(
                title = stringResource(R.string.lblRestartStatistics),
                descriptionText = stringResource(R.string.lblRestartStatisticsDescription, "Nunca"),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {

                    }
                    .padding(16.dp)
            )

            HorizontalDivider(modifier = Modifier
                .graphicsLayer(alpha = 0.5f)
                .padding(top = 8.dp, bottom = 16.dp)
            )

            // informacion de almacenamiento
            Column(
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                // encabezado
                Text(
                    text = stringResource(R.string.lblRedUse),
                    style = typography.labelLarge
                )

                Box {
                    Box(
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                            .background(colorScheme.surfaceContainerHighest, shapes.extraLarge)
                            .padding(horizontal = 16.dp)
                            .fillMaxWidth()
                            .height(5.dp)
                    )

                    Box(
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                            .background(orange, shapes.extraLarge)
                            .padding(horizontal = 16.dp)
                            .fillMaxWidth(
                                usedStorage.toFloat() / totalStorage.toFloat()
                            )
                            .height(5.dp)
                    )
                }

                // espacio aplicacion
                // espacio total celular
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = formatBytes(usedStorage),
                        style = typography.headlineSmall,
                        modifier = Modifier.graphicsLayer(alpha = 0.5f)
                    )

                    Spacer(Modifier.weight(1f))

                    Text(
                        text = formatBytes(availableStorage),
                        style = typography.headlineSmall,
                        modifier = Modifier.graphicsLayer(alpha = 0.5f)
                    )
                }

                // en uso y libres
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(R.string.lblUsing),
                        style = typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.graphicsLayer(alpha = 0.5f)
                    )

                    Spacer(Modifier.weight(1f))

                    Text(
                        text = stringResource(R.string.lblFreeStorage),
                        style = typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.graphicsLayer(alpha = 0.5f)
                    )
                }

                Row(
                    modifier = Modifier.padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .background(orange, shapes.extraLarge)
                    )

                    Spacer(Modifier.size(8.dp))

                    Text(
                        text = stringResource(R.string.lblRumappSpaceUsed, formatBytes(appStorage)),
                        style = typography.labelMedium
                    )
                }
            }

            HorizontalDivider(modifier = Modifier
                .graphicsLayer(alpha = 0.5f)
                .padding(top = 16.dp, bottom = 8.dp)
            )

            InfoRowField(
                title = stringResource(R.string.lblFreeUpSpace),
                descriptionText = stringResource(R.string.lblFreeUpSpaceDescription, "Nunca"),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {

                    }
                    .padding(16.dp)
            )
        }
    }
}