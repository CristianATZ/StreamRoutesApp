package net.streamroutes.sreamroutesapp.Screens.MenuScreens

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController

@Composable
fun RoutesScreen(navController: NavController){
    Routes(navController = navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Routes(navController: NavController){

}
@Preview(showBackground = true)
@Composable
fun RoutesScreenView(){

}