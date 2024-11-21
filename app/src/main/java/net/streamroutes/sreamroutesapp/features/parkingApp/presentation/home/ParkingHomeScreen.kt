package net.streamroutes.sreamroutesapp.features.parkingApp.presentation.home

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.DirectionsBike
import androidx.compose.material.icons.outlined.DirectionsBus
import androidx.compose.material.icons.outlined.DirectionsCar
import androidx.compose.material.icons.outlined.Motorcycle
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.compose.orange
import kotlinx.coroutines.launch
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.core.domain.model.CategoryVehicle
import net.streamroutes.sreamroutesapp.core.domain.model.FilterCost
import net.streamroutes.sreamroutesapp.core.domain.model.FilterParking
import net.streamroutes.sreamroutesapp.core.domain.model.FilterSpace
import net.streamroutes.sreamroutesapp.core.domain.model.ParkingCategory
import net.streamroutes.sreamroutesapp.core.navigation.ParkingNavigation
import net.streamroutes.sreamroutesapp.features.authentication.presentation.login.LoginViewModel
import net.streamroutes.sreamroutesapp.features.maps.components.ElementOption
import net.streamroutes.sreamroutesapp.features.maps.presentation.transport.ShimmerTransportScreen
import net.streamroutes.sreamroutesapp.features.parkingApp.components.CategoryItem
import net.streamroutes.sreamroutesapp.features.parkingApp.components.ParkingDrawerContent
import net.streamroutes.sreamroutesapp.features.parkingApp.components.ParkingSmallTopAppBar
import net.streamroutes.sreamroutesapp.features.profile.presentation.profile.ProfileViewModel

@Composable
fun ParkingMain(
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    val navHostController = rememberNavController()

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    val openDrawer = {
        coroutineScope.launch {
            drawerState.open()
        }
    }

    val closeDrawer = {
        coroutineScope.launch {
            drawerState.close()
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ParkingDrawerContent(
                navHostController = navHostController,
                closeDrawer = {
                    closeDrawer()
                },
                onLogOut = {
                    loginViewModel.signOut()
                }
            )
        }
    ) {
        ParkingNavigation(
            navHostController = navHostController,
            onOpenMenu = {
                openDrawer()
            }
        )
    }
}

@SuppressLint("UnusedContentLambdaTargetStateParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ParkingHomeScreen(
    onOpenMenu: () -> Unit,
    onSettingsPressed: () -> Unit,
    onProfilePressed: () -> Unit,
    onSelectParking: () -> Unit,
    parkingViewModel: ParkingViewModel = hiltViewModel(),
    profileViewModel: ProfileViewModel = hiltViewModel()
) {
    val currentUser by profileViewModel.userData.collectAsState()
    val parkings by parkingViewModel.parkings.collectAsState()

    var query by remember {
        mutableStateOf("")
    }
    var viewall by remember {
        mutableStateOf(false)
    }

    var openFilter by remember {
        mutableStateOf(false)
    }

    var filters by remember {
        mutableStateOf(FilterParking())
    }

    var spaceFilter by remember {
        mutableStateOf(filters.space)
    }

    var costFilter by remember {
        mutableStateOf(filters.cost)
    }

    fun updateFilters(space: FilterSpace, cost: FilterCost) {
        filters = FilterParking(space, cost)
    }

    val onSearch = {

    }

    var categoryVehicle by remember {
        mutableStateOf(CategoryVehicle.CAR)
    }

    val categoryList = listOf(
        ParkingCategory(stringResource(R.string.lblCar), Icons.Outlined.DirectionsCar, CategoryVehicle.CAR) { categoryVehicle = CategoryVehicle.CAR },
        ParkingCategory(stringResource(R.string.lblMoto), Icons.Outlined.Motorcycle, CategoryVehicle.MOTO) { categoryVehicle = CategoryVehicle.MOTO },
        ParkingCategory(stringResource(R.string.lblBike), Icons.AutoMirrored.Outlined.DirectionsBike, CategoryVehicle.BIKE) { categoryVehicle = CategoryVehicle.BIKE },
        ParkingCategory(stringResource(R.string.lblBus), Icons.Outlined.DirectionsBus, CategoryVehicle.BUS) { categoryVehicle = CategoryVehicle.BUS},
    )

    Scaffold(
        topBar = {
            ParkingSmallTopAppBar(
                onNavigationPressed = onOpenMenu,
                onSettingsPressed = onSettingsPressed,
                onProfilePressed = onProfilePressed,
                onFilterPressed = {
                    openFilter = !openFilter
                },
                user = currentUser?.username ?: "USUARIO"
            )
        }
    ) { innerPadding ->
        if(parkings != null) {
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                // filtros
                AnimatedVisibility(
                    visible = openFilter
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        // filtros de espacio
                        Row(
                            modifier = Modifier.padding(horizontal = 16.dp)
                        ) {
                            FilterChip(
                                selected = spaceFilter == FilterSpace.ALL,
                                label = {
                                    Text(text = stringResource(R.string.lblFilterAll))
                                },
                                onClick = {
                                    spaceFilter = FilterSpace.ALL
                                }
                            )

                            Spacer(Modifier.size(8.dp))

                            FilterChip(
                                selected = spaceFilter == FilterSpace.FREE,
                                label = {
                                    Text(text = stringResource(R.string.lblFilterFreeSpace))
                                },
                                onClick = {
                                    spaceFilter = FilterSpace.FREE
                                }
                            )

                            Spacer(Modifier.size(8.dp))

                            FilterChip(
                                selected = spaceFilter == FilterSpace.BUSY,
                                label = {
                                    Text(text = stringResource(R.string.lblFilterBusySpace))
                                },
                                onClick = {
                                    spaceFilter = FilterSpace.BUSY
                                }
                            )
                        }

                        // filtros por costo
                        Row(
                            modifier = Modifier.padding(horizontal = 16.dp)
                        ) {
                            FilterChip(
                                selected = costFilter == FilterCost.ALL,
                                label = {
                                    Text(text = stringResource(R.string.lblFilterAll))
                                },
                                onClick = {
                                    costFilter = FilterCost.ALL
                                }
                            )

                            Spacer(Modifier.size(8.dp))

                            FilterChip(
                                selected = costFilter == FilterCost.FREE,
                                label = {
                                    Text(text = stringResource(R.string.lblFilterFreeCost))
                                },
                                onClick = {
                                    costFilter = FilterCost.FREE
                                }
                            )

                            Spacer(Modifier.size(8.dp))

                            FilterChip(
                                selected = costFilter == FilterCost.COST,
                                label = {
                                    Text(text = stringResource(R.string.lblFilterCost))
                                },
                                onClick = {
                                    costFilter = FilterCost.COST
                                }
                            )
                        }

                        // aplicar filtros
                        Button(
                            onClick = {
                                updateFilters(spaceFilter, costFilter)
                                openFilter = !openFilter
                            },
                            shape = shapes.small,
                            modifier = Modifier
                                .align(Alignment.End)
                                .padding(horizontal = 16.dp)
                        ) {
                            Text(text = stringResource(R.string.lblApply))
                        }

                        Spacer(Modifier.size(16.dp))
                        HorizontalDivider()
                    }
                }

                AnimatedVisibility(
                    visible = !viewall
                ) {
                    Column {
                        // barra de busqueda
                        SearchBar(
                            query = query,
                            onQueryChange = { query = it },
                            onSearch = {
                                query = it
                                onSearch()
                            },
                            active = false,
                            onActiveChange = {

                            },
                            trailingIcon = {
                                IconButton(
                                    onClick = {
                                        onSearch()
                                    }
                                ) {
                                    Icon(
                                        imageVector = Icons.Outlined.Search,
                                        contentDescription = stringResource(id = R.string.iconSearch)
                                    )
                                }
                            },
                            placeholder = {
                                Text(text = stringResource(id = R.string.lblSearch))
                            },
                            shadowElevation = 4.dp,
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .fillMaxWidth()
                        ) {

                        }

                        // categorias
                        LazyRow(
                            contentPadding = PaddingValues(16.dp)
                        ) {
                            items(categoryList) { item ->
                                CategoryItem (
                                    icon = item.icon,
                                    selected = categoryVehicle == item.categoryVehicle,
                                    label = item.label,
                                    onSelectCategory = item.onSelectCategory
                                )

                                Spacer(Modifier.size(16.dp))
                            }
                        }
                    }
                }

                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    item {
                        // espacios cercanos, boton de ver mas
                        Row(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = stringResource(R.string.lblNearSpace),
                                style = typography.labelLarge,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.graphicsLayer(alpha = 0.5f)
                            )

                            Spacer(Modifier.weight(1f))

                            TextButton(
                                onClick = {
                                    viewall = !viewall
                                }
                            ) {
                                Text(
                                    text = stringResource(R.string.lblViewAll),
                                    style = typography.labelLarge,
                                    color = orange,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }

                    /*
                    items(5) {
                        ElementOption(
                            title = "ITSUR",
                            description = "Av. Educacion Superior, 38890",
                            price = "31",
                            calification = "3.8",
                            onClick = onSelectParking
                        )

                        Spacer(Modifier.size(16.dp))
                    }*/

                    items(parkings!!) { parking ->
                        ElementOption(
                            title = parking.place.name,
                            description = "${parking.place.street}, ${parking.place.suburb}, ${parking.place.state}",
                            price = parking.parking.feePerHour.toString(),
                            calification = parking.parking.rating.toString(),
                            onClick = {
                                parkingViewModel.selectParking(parking)
                                onSelectParking()
                            },
                            url = parking.place.imageUrl
                        )
                    }
                }
            }
        } else {
            ShimmerTransportScreen(
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}