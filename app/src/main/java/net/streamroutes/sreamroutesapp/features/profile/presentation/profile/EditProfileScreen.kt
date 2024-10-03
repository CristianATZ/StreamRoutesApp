package net.streamroutes.sreamroutesapp.features.profile.presentation.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import net.streamroutes.sreamroutesapp.features.profile.components.ProfileSmallTopAppBar

@Composable
fun EditProfileScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        ProfileSmallTopAppBar(
            onBackPressed = { }
        )
    }
}