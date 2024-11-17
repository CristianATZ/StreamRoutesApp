package net.streamroutes.sreamroutesapp.features.settings.presentation.maps

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import net.streamroutes.sreamroutesapp.features.settings.data.MapsSettingsRepositoryImp
import javax.inject.Inject

@HiltViewModel
class MapSettingsViewModel @Inject constructor(
    private val mapsSettingsRepository: MapsSettingsRepositoryImp
) : ViewModel() {
    val mapTheme: StateFlow<Boolean> = mapsSettingsRepository.mapTheme
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = false
        )

    val routeColor: StateFlow<Int> = mapsSettingsRepository.routeColor
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = Color.Black.toArgb()
        )

    val stopColor: StateFlow<Int> = mapsSettingsRepository.stopColor
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = Color.Green.toArgb()
        )

    val lineSize: StateFlow<Int> = mapsSettingsRepository.lineSize
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = 1
        )

    fun changeMapTheme(mapTheme: Boolean) {
        viewModelScope.launch {
            mapsSettingsRepository.changeMapTheme(mapTheme)
        }
    }

    fun changeRouteColor(routeColor: Color) {
        viewModelScope.launch {
            mapsSettingsRepository.changeRouteColor(routeColor)
        }
    }

    fun changeStopColor(stopColor: Color) {
        viewModelScope.launch {
            mapsSettingsRepository.changeStopColor(stopColor)
        }
    }

    fun changeLineSize(lineSize: Int) {
        viewModelScope.launch {
            mapsSettingsRepository.changeLineSize(lineSize)
        }
    }
}