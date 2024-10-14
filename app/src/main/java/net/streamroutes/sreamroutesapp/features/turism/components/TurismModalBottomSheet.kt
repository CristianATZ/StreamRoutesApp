package net.streamroutes.sreamroutesapp.features.turism.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MoreHoriz
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.core.domain.model.TurismInformation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TurismModalBottomSheet(
    sheetState: SheetState = rememberModalBottomSheetState(),
    turismInformation: TurismInformation = TurismInformation(
        name = "Alhondiga de granaditas",
        totalRoutes = 4,
        calendar = "08:00 - 16:00",
        price = "36 MXN",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim adLorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim adLorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad"
    ),
    onDismiss: () -> Unit,
    onSelectRoute: () -> Unit = {},
    onMore: () -> Unit
) {
    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = onDismiss
    ) {
        Column(
            modifier = Modifier
                .navigationBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // nombre y ver mas
            Box(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = turismInformation.name,
                    style = typography.headlineSmall,
                    modifier = Modifier
                        .align(Alignment.Center)
                )

                FilledTonalIconButton(
                    onClick = onMore,
                    colors = IconButtonDefaults.filledTonalIconButtonColors(
                        containerColor = colorScheme.tertiary,
                        contentColor = colorScheme.onTertiary
                    ),
                    modifier = Modifier.align(Alignment.CenterEnd)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.MoreHoriz,
                        contentDescription = stringResource(id = R.string.iconMoreTurism)
                    )
                }

            }

            Spacer(modifier = Modifier.size(16.dp))

            // informacion del punto turistico
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = R.string.lblTurismDetails),
                    style = typography.titleLarge,
                    modifier = Modifier.graphicsLayer(alpha = 0.5f)
                )

                Text(
                    text = stringResource(id = R.string.lblTurismTotalRoutes, turismInformation.totalRoutes),
                    style = typography.labelLarge
                )

                Text(
                    text = stringResource(id = R.string.lblTurismCalendar, turismInformation.calendar),
                    style = typography.labelLarge
                )

                Text(
                    text = stringResource(id = R.string.lblTurismPrice, turismInformation.price ?: stringResource(id = R.string.lblTurismFree)),
                    style = typography.labelLarge
                )

                Spacer(modifier = Modifier.size(8.dp))

                Text(
                    text = stringResource(id = R.string.lblDescription),
                    style = typography.titleLarge,
                    modifier = Modifier.graphicsLayer(alpha = 0.5f)
                )

                Text(
                    text = turismInformation.description,
                    style = typography.labelLarge,
                    textAlign = TextAlign.Justify
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