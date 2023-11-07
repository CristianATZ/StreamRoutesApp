package net.streamroutes.sreamroutesapp.Screens.Routes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.utsman.osmandcompose.DefaultMapProperties
import com.utsman.osmandcompose.OpenStreetMap
import com.utsman.osmandcompose.ZoomButtonVisibility
import net.streamroutes.sreamroutesapp.MyViewModel
import net.streamroutes.sreamroutesapp.Navigation.AppScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FastScreen(
    navController: NavHostController,
    myViewModel: MyViewModel
) {
    var dialogo by remember {
        mutableStateOf(true)
    }

    if(dialogo){
        DialogStart(
            myViewModel
        ){
            dialogo = !dialogo
        }
    }

    Scaffold(
        topBar = { TopBarBody(navController,myViewModel) }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
        ) {

            OpenStreetMap(
                modifier = Modifier
                    .fillMaxSize(),
                properties = DefaultMapProperties.copy(
                    zoomButtonVisibility = ZoomButtonVisibility.NEVER
                )
            ) {

            }

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.weight(1f))

                Button(
                    onClick = {
                        navController.navigate(route = AppScreens.MainScreen.route)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorScheme.tertiary
                    ),
                    shape = RoundedCornerShape(16),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 5.dp
                    ),
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .height(50.dp)
                ) {
                    Text(
                        text = myViewModel.languageType()[325],
                        style = typography.bodyLarge,
                        color = colorScheme.onTertiary
                    )
                }
                
                Spacer(modifier = Modifier.size(16.dp))
            }
        }
    }
}

@Composable
fun DialogStart(
    myViewModel: MyViewModel,
    onClick: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { onClick() },
        confirmButton = {
            TextButton(
                onClick = { onClick() }
            ) {
                Text(text = myViewModel.languageType()[323])
            }
        },
        title = {
            Text(
                text = myViewModel.languageType()[321]
            )
        },
        text = {
            Text(
                text = myViewModel.languageType()[322]
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBarBody(
    navController: NavHostController,
    myViewModel: MyViewModel
) {
    Column(
        modifier = Modifier
            .background(colorScheme.background)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { navController.navigate(AppScreens.MainScreen.route) }
            ) {
                Icon(
                    imageVector = Icons.Outlined.KeyboardArrowLeft,
                    contentDescription = "Regresar al menu principal",
                    modifier = Modifier
                        .size(24.dp)
                )
            }

            OutlinedTextField(
                value = "",
                onValueChange = {},
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Search,
                        contentDescription = null,
                        tint = colorScheme.onPrimaryContainer
                    )
                },
                trailingIcon = {

                },
                placeholder = {
                    Text(
                        text = myViewModel.languageType()[324],
                        color = colorScheme.onPrimaryContainer.copy(0.5f)
                    )
                },
                shape = RoundedCornerShape(15),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier
                    .weight(1f)
            )
            
            Spacer(modifier = Modifier.size(8.dp))
        }
    }
}