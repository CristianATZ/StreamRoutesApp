package net.streamroutes.sreamroutesapp.features.turism.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.features.turism.components.TurismSmallTopAppBar
import net.streamroutes.sreamroutesapp.features.turism.presentation.turismList.TurismListScreen
import net.streamroutes.sreamroutesapp.features.turism.presentation.turismMap.TurismMapScreen
import net.streamroutes.sreamroutesapp.features.turism.presentation.turismRoute.TurismRouteScreen

@Composable
fun TurismScreen(
    onBackPressed: () -> Unit
) {
    var currenTab by remember {
        mutableIntStateOf(0)
    }

    val onChangeTab = { index: Int ->
        currenTab = index
    }

    Scaffold(
        topBar = {
            TurismSmallTopAppBar(
                title = stringResource(id = R.string.lblTourism),
                onBackPressed = onBackPressed
            )
        }
    ) { innerPadding ->

        when(currenTab) {
            0 -> {
                TurismListScreen(
                    onViewMap = {
                        onChangeTab(1)
                    },
                    onSelectPoint = {
                        onChangeTab(2)
                    },
                    modifier = Modifier.padding(innerPadding)
                )
            }
            1 -> {
                TurismMapScreen(
                    onBackPressed = {
                        onChangeTab(0)
                    }
                )
            }
            2 -> {
                TurismRouteScreen(
                    onShareLocation = {

                    },
                    onBackPressed = {
                        onChangeTab(0)
                    }
                )
            }
        }
    }
}
