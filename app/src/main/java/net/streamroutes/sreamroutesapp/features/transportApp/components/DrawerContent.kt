package net.streamroutes.sreamroutesapp.features.transportApp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Logout
import androidx.compose.material.icons.outlined.Forum
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material.icons.outlined.Map
import androidx.compose.material.icons.outlined.Navigation
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.orange
import net.streamroutes.sreamroutesapp.R

@Preview
@Composable
fun DrawerContent(
    modifier: Modifier = Modifier,
    //onItemSelected: (String) -> Unit,
) {
    ModalDrawerSheet(
        drawerShape = RectangleShape,
        modifier = modifier
            .fillMaxWidth(0.8f),
    ) {
        Text(
            text = stringResource(id = R.string.lblRumapp),
            style = typography.titleLarge,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.Start)
        )

        HorizontalDivider(modifier = Modifier.padding(bottom = 16.dp))

        // inicio
        DrawerItem(
            label = stringResource(id = R.string.lblHome),
            icon = Icons.Outlined.Home,
            iconDescription = stringResource(id = R.string.iconHome),
            selected = false,
            onClick = {
                // ENVIAR A INICIO
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        // paquetes
        DrawerItem(
            label = stringResource(id = R.string.lblPremium),
            icon = Icons.Outlined.ShoppingCart,
            iconDescription = stringResource(id = R.string.iconPremium),
            selected = false,
            onClick = {
                // ENVIAR A PAQUETES
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        // Mapas
        DrawerItem(
            label = stringResource(id = R.string.lblMaps),
            icon = Icons.Outlined.Map,
            iconDescription = stringResource(id = R.string.iconMaps),
            selected = false,
            onClick = {
                // ENVIAR A MAPAS
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        // Turismo
        DrawerItem(
            label = stringResource(id = R.string.lblTurism),
            icon = Icons.Outlined.Navigation,
            iconDescription = stringResource(id = R.string.iconTurism),
            selected = false,
            onClick = {
                // ENVIAR A TURISMO
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        // Turismo
        DrawerItem(
            label = stringResource(id = R.string.lblForum),
            icon = Icons.Outlined.Forum,
            iconDescription = stringResource(id = R.string.iconForum),
            selected = false,
            onClick = {
                // ENVIAR A FORO
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        // Configuracion
        DrawerItem(
            label = stringResource(id = R.string.lblSettings),
            icon = Icons.Outlined.Settings,
            iconDescription = stringResource(id = R.string.iconSettings),
            selected = false,
            onClick = {
                // ENVIAR A CONFIGURACION
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        HorizontalDivider()

        Spacer(modifier = Modifier.size(16.dp))

        // Cerrar sesion
        DrawerItem(
            label = stringResource(id = R.string.lblLogout),
            icon = Icons.AutoMirrored.Outlined.Logout,
            iconDescription = stringResource(id = R.string.iconLogout),
            selected = true,
            logout = true,
            onClick = {
                // Cerrar sesion
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun DrawerItem(
    modifier: Modifier = Modifier,
    label: String = "Ejemplo",
    icon: ImageVector = Icons.Outlined.Home,
    iconDescription: String = "Ejemplo",
    selected: Boolean = false,
    logout: Boolean = false,
    onClick: () -> Unit = {}
) {

    val colors = if(!logout) {
        NavigationDrawerItemDefaults.colors(
            selectedContainerColor = orange.copy(0.25f),
            selectedTextColor = orange,
            selectedIconColor = orange,
        )
    } else {
        NavigationDrawerItemDefaults.colors(
            selectedContainerColor = colorScheme.errorContainer,
            selectedTextColor = colorScheme.onErrorContainer,
            selectedIconColor = colorScheme.onErrorContainer,
        )
    }

    NavigationDrawerItem(
        label = {
            Text(
                text = label,
                fontWeight = FontWeight.Bold
            )
        },
        icon = {
            Icon(
                imageVector = icon,
                contentDescription = iconDescription
            )
        },
        selected = selected,
        shape = shapes.small,
        onClick = onClick,
        colors = colors,
        modifier = modifier
            .padding(bottom = 16.dp)
            .fillMaxWidth(0.9f)
            .height(50.dp)
    )
}