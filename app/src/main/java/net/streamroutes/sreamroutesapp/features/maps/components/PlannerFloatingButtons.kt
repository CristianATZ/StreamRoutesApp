package net.streamroutes.sreamroutesapp.features.maps.components

import android.graphics.Paint.Align
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.MyLocation
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.LatLng
import net.streamroutes.sreamroutesapp.R

@Composable
fun PlannerFloatingButtons(
    onMyLocation: (LatLng) -> Unit,
    onAddItem: () -> Unit,
    onOpenList: () -> Unit,
    onCancelItem: () -> Unit,
    isVisible: Boolean,
    modifier: Modifier
) {
    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn(),
        exit = fadeOut(),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.End
        ) {
            // AGREGAR ITEM A LA LISTA
            FloatingActionButton(
                onClick = onAddItem,
                shape = shapes.medium,
                containerColor = colorScheme.primaryContainer,
                contentColor = colorScheme.onPrimaryContainer
            ) {
                Icon(
                    imageVector = Icons.Outlined.Add,
                    contentDescription = stringResource(id = R.string.iconAddDestination)
                )
            }

            Spacer(modifier = Modifier.size(8.dp))

            // cancelar item
            FloatingActionButton(
                onClick = onCancelItem,
                shape = shapes.medium,
                containerColor = colorScheme.errorContainer,
                contentColor = colorScheme.onErrorContainer
            ) {
                Icon(
                    imageVector = Icons.Outlined.Cancel,
                    contentDescription = stringResource(id = R.string.iconCancelDestination)
                )
            }
        }
    }

    AnimatedVisibility(
        visible = !isVisible,
        enter = fadeIn(),
        exit = fadeOut(),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.End
        ) {
            // cambiar camara a mi ubicacion actual
            FloatingActionButton(
                onClick = {
                    // OBTENER UBICACION ACTUAL Y PASAR EL PARAMETRO
                    //onMyLocation()
                },
                shape = shapes.medium,
                containerColor = colorScheme.tertiaryContainer,
                contentColor = colorScheme.onTertiaryContainer
            ) {
                Icon(
                    imageVector = Icons.Outlined.MyLocation,
                    contentDescription = stringResource(id = R.string.iconMyLocation)
                )
            }

            Spacer(modifier = Modifier.size(8.dp))

            // ver lista
            ExtendedFloatingActionButton(
                onClick = onOpenList,
                shape = shapes.medium,
            ) {
                Text(text = stringResource(id = R.string.lblMyList))
            }
        }
    }
}