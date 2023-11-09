package net.streamroutes.sreamroutesapp.Screens.Routes

import android.graphics.drawable.Drawable
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.MyLocation
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.android.gms.maps.model.LatLng
import com.utsman.osmandcompose.DefaultMapProperties
import com.utsman.osmandcompose.Marker
import com.utsman.osmandcompose.MarkerState
import com.utsman.osmandcompose.OpenStreetMap
import com.utsman.osmandcompose.ZoomButtonVisibility
import com.utsman.osmandcompose.rememberCameraState
import net.streamroutes.sreamroutesapp.MyViewModel
import net.streamroutes.sreamroutesapp.Navigation.AppScreens
import net.streamroutes.sreamroutesapp.R
import org.osmdroid.util.GeoPoint

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


    var selectedLocation by remember { mutableStateOf<LatLng?>(null) }

    val cameraState = rememberCameraState {
        geoPoint = GeoPoint(19.035229199074546, -98.23207582752717)
        zoom = 17.0
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
                ),
                onMapClick = {
                    selectedLocation = LatLng(it.latitude,it.longitude)
                },
                cameraState = cameraState
            ) {
                selectedLocation?.let {
                    com.utsman.osmandcompose.Marker(
                        state = com.utsman.osmandcompose.MarkerState(
                            geoPoint = GeoPoint(selectedLocation!!.latitude, selectedLocation!!.longitude)
                        )
                    )
                }

                Marker(
                    state = MarkerState(GeoPoint(19.035229199074546, -98.23207582752717)),
                ) {

                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.weight(1f))

                Button(
                    onClick = {
                        selectedLocation = null
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
    var destino by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .background(colorScheme.primary)
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
                        .size(24.dp),
                    tint = colorScheme.onPrimary
                )
            }

            OutlinedTextField(
                value = destino,
                onValueChange = { destino = it },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Search,
                        contentDescription = null,
                        tint = colorScheme.onPrimaryContainer
                    )
                },
                trailingIcon = {
                    if(destino.isNotEmpty()){
                        IconButton(onClick = { destino = "" }) {
                            Icon(
                                imageVector = Icons.Outlined.Close,
                                contentDescription = null,
                                tint = colorScheme.onPrimaryContainer
                            )
                        }
                    }
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
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = colorScheme.primaryContainer,
                    unfocusedBorderColor = colorScheme.primaryContainer,
                    focusedContainerColor = colorScheme.primaryContainer,
                    focusedTextColor = colorScheme.onPrimaryContainer
                ),
                modifier = Modifier
                    .weight(1f)
            )
            
            Spacer(modifier = Modifier.size(8.dp))
        }
    }
}