package net.streamroutes.sreamroutesapp.features.maps.presentation.fastest

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MyLocation
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.features.maps.components.RouteDetails

@OptIn(ExperimentalFoundationApi::class)
@Preview(showBackground = true)
@Composable
fun FastestRoute(
    currentRoute: String = "",
    onCancelRoute: () -> Unit ={},
    onMyLocation: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.End
    ) {
        RouteDetails()

        Spacer(modifier = Modifier.weight(1f))

        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.End
        ) {
            FloatingActionButton(
                onClick = onMyLocation,
                containerColor = colorScheme.tertiaryContainer,
                contentColor = colorScheme.onTertiaryContainer
            ) {
                Icon(
                    imageVector = Icons.Outlined.MyLocation,
                    contentDescription = stringResource(id = R.string.iconMyLocation)
                )
            }

            Spacer(modifier = Modifier.size(8.dp))

            ExtendedFloatingActionButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .combinedClickable(
                        onClick = {

                        },
                        onLongClick = {
                            // CANCELAR RUTA
                            onCancelRoute()
                        }
                    )
            ) {
                Text(text = stringResource(id = R.string.btnCancelRoute))
            }
        }
    }
}
