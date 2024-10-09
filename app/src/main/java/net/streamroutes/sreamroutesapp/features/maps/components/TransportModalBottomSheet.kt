package net.streamroutes.sreamroutesapp.features.maps.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Download
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.compose.RumappAppTheme
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.core.domain.model.RouteInformation
import net.streamroutes.sreamroutesapp.features.components.CardOption

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransportModalBottomSheet(
    sheetState: SheetState = rememberModalBottomSheetState(),
    onDismiss: () -> Unit = {},
    onDownloadRoute: () -> Unit = {},
    onSelectRoute: () -> Unit = {},
    routeInformation: RouteInformation = RouteInformation(
        name = "Ruta 11 - El charco",
        countTurism = 4,
        hourAprox = 2,
        minutesAprox = 30,
        officialStops = 16,
        start = "C. Pipila, Col. Ninios Herores",
        end = "C. Francisco Marquez, Col. Zona Centro"
    )
) {
    // CARGAR LA INFORMACION DEL ITEM
    // EN LUGAR DE PASAR EL HISTORIAL ITEM

    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = onDismiss
    ) {
        Column(
            modifier = Modifier
                .navigationBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // nombre de la ruta
            Box(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = routeInformation.name,
                    style = typography.headlineSmall,
                    modifier = Modifier
                        .align(Alignment.Center)
                )

                FilledTonalIconButton(
                    onClick = onDownloadRoute,
                    colors = IconButtonDefaults.filledTonalIconButtonColors(
                        containerColor = colorScheme.tertiary,
                        contentColor = colorScheme.onTertiary
                    ),
                    modifier = Modifier.align(Alignment.CenterEnd)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Download,
                        contentDescription = stringResource(id = R.string.iconDownload)
                    )
                }

            }

            // lugares turisticos
            LazyRow(
                contentPadding = PaddingValues(16.dp)
            ) {
                items(routeInformation.officialStops) {
                    CardOption(
                        modifier = Modifier.size(200.dp)
                    )

                    Spacer(modifier = Modifier.size(16.dp))
                }
            }

            // infoemacion de la ruta
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = R.string.lblRouteDetails),
                    style = typography.titleLarge,
                    modifier = Modifier.graphicsLayer(alpha = 0.5f)
                )

                // tiempo aproximado
                Text(
                    text = stringResource(id = R.string.lblAproxTime, routeInformation.hourAprox, routeInformation.minutesAprox),
                    style = typography.labelLarge
                )

                Spacer(modifier = Modifier.size(8.dp))

                // paradas oficiales
                Text(
                    text = stringResource(
                        id = R.string.lblOfficialStops,
                        routeInformation.officialStops
                    ),
                    style = typography.labelLarge
                )

                Spacer(modifier = Modifier.size(8.dp))

                // inicio de ruta
                Text(
                    text = stringResource(id = R.string.lblStartRoute, routeInformation.start),
                    style = typography.labelLarge
                )

                Spacer(modifier = Modifier.size(8.dp))

                // fin de ruta
                Text(
                    text = stringResource(id = R.string.lblEndRoute, routeInformation.end),
                    style = typography.labelLarge
                )
            }

            Spacer(modifier = Modifier.size(16.dp))

            // regresar
            OutlinedButton(
                onClick = onDismiss,
                shape = shapes.small,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth()
            ) {
                Text(text = stringResource(id = R.string.btnBack))
            }

            // seleccionar ruta
            Button(
                onClick = onSelectRoute,
                shape = shapes.small,
                modifier = Modifier
                    .padding(bottom = 16.dp, start = 16.dp, end = 16.dp)
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth()
            ) {
                Text(text = stringResource(id = R.string.btnSelect))
            }
        }
    }
}