package net.streamroutes.sreamroutesapp.features.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.TopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(
    modifier: Modifier = Modifier,
    title: @Composable () -> Unit, // Título como un composable
    navigationIcon: @Composable () -> Unit = { }, // Ícono de navegación opcional
    actions: @Composable RowScope.() -> Unit = {} // Acciones en la parte derecha de la barra
) {
    TopAppBar(
        title = title, // Paso el parámetro recibido
        navigationIcon = navigationIcon, // Ícono de navegación
        actions = actions, // Acciones
        modifier = modifier
    )
}