package net.streamroutes.sreamroutesapp.features.transportApp.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.features.components.CardOption
import net.streamroutes.sreamroutesapp.features.profile.presentation.profile.ProfileViewModel
import net.streamroutes.sreamroutesapp.features.transportApp.components.TransportSmallTopAppBar

@Composable
fun HomeScreen(
    profileViewModel: ProfileViewModel = hiltViewModel(),
    onMenuPressed: () -> Unit,
    onMapsPressed: () -> Unit,
    onTourismPressed: () -> Unit,
    onForumPressed: () -> Unit
) {
    val userData by profileViewModel.userData.collectAsState()

    Scaffold(
        topBar = {
            TransportSmallTopAppBar(
                onNavigationPressed = onMenuPressed
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = R.string.lblHello, userData?.username ?: "USUARIO"),
                    style = typography.displaySmall
                )
                Text(
                    text = stringResource(id = R.string.lblGreet),
                    style = typography.bodyMedium,
                    modifier = Modifier.graphicsLayer(alpha = 0.5f)
                )
            }

            Column(
                modifier = Modifier.fillMaxSize().padding(bottom = 16.dp),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                CardOption(
                    text = stringResource(id = R.string.lblMaps),
                    onClick = onMapsPressed,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth()
                        .height(200.dp)
                )

                CardOption(
                    text = stringResource(id = R.string.lblTourism),
                    onClick = onTourismPressed,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth()
                        .height(200.dp)
                )

                CardOption(
                    text = stringResource(id = R.string.lblForum),
                    onClick = onForumPressed,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth()
                        .height(200.dp)
                )
            }
        }
    }
}