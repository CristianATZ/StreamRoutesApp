package net.streamroutes.sreamroutesapp.features.maps.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import net.streamroutes.sreamroutesapp.features.maps.components.MapsSmallTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun MapsScreen(
    modifier: Modifier = Modifier
) {

    var query by remember {
        mutableStateOf("")
    }

    Scaffold(
        topBar = {
            MapsSmallTopAppBar()
        }
    ) { innrPadding ->
        // UTILIZAR UN NAVHOST EN ESTE SCAFFOLD
        // PARA MANEJAR TRANSPORTE, PLANIFICA Y CAMINA
        // DENTRO DE TRANSPORTE MANEJAR OTRO NAVHOST
        // PARA MANEJAR LA RUTA Y EL MAPA COMPLETO


        Column(
            modifier = Modifier
                .padding(innrPadding)
                .fillMaxSize()
        ) {
            SearchBar(
                query = query,
                onQueryChange = { query = it },
                onSearch = { query = it },
                active = false,
                onActiveChange = {}
            ) {
                
            }
        }
    }
}