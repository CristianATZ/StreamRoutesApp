package net.streamroutes.sreamroutesapp.features.settings.data

import android.content.Context
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.datastore.preferences.core.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import net.streamroutes.sreamroutesapp.core.data.datastore.PreferenceKeys.MAP_THEME
import net.streamroutes.sreamroutesapp.core.data.datastore.PreferenceKeys.NEAR_STOP_COLOR
import net.streamroutes.sreamroutesapp.core.data.datastore.PreferenceKeys.ROUTE_COLOR
import net.streamroutes.sreamroutesapp.core.data.datastore.PreferenceKeys.WIDTH_LINE_SIZE
import net.streamroutes.sreamroutesapp.core.data.datastore.mapsDataStore
import javax.inject.Inject
import javax.inject.Singleton

interface MapsSettingsRepository {
    suspend fun changeMapTheme(mapTheme: Boolean)
    suspend fun changeRouteColor(colorRoute: Color)
    suspend fun changeStopColor(colorStop: Color)
    suspend fun changeLineSize(lineSize: Int)
}

@Singleton
class MapsSettingsRepositoryImp @Inject constructor(
    @ApplicationContext private val context: Context
) : MapsSettingsRepository {

    private val dataStore = context.mapsDataStore

    // leet tema del mapa
    val mapTheme: Flow<Boolean> = dataStore.data
        .map { preferences ->
            preferences[MAP_THEME] ?: false // TEMA CLARO POR DEFECTO
        }

    // leer color de la ruta
    val routeColor: Flow<Int> = dataStore.data
        .map { preferences ->
            preferences[ROUTE_COLOR] ?: Color.Black.toArgb() // TEMA CLARO POR DEFECTO
        }

    // leer color de la ruta a la parada mas cercana
    val stopColor: Flow<Int> = dataStore.data
        .map { preferences ->
            preferences[NEAR_STOP_COLOR] ?: Color.Green.toArgb() // TEMA CLARO POR DEFECTO
        }

    // leer ancho de la linea
    val lineSize: Flow<Int> = dataStore.data
        .map { preferences ->
            preferences[WIDTH_LINE_SIZE] ?: 1 // TEMA CLARO POR DEFECTO
        }

    override suspend fun changeMapTheme(mapTheme: Boolean) {
        dataStore.edit { preferences ->
            preferences[MAP_THEME] = mapTheme
        }
    }

    override suspend fun changeRouteColor(r: Color) {
        dataStore.edit { preferences ->
            preferences[ROUTE_COLOR] = r.toArgb()
        }
    }

    override suspend fun changeStopColor(colorStop: Color) {
        dataStore.edit { preferences ->
            preferences[NEAR_STOP_COLOR] = colorStop.toArgb()
        }
    }

    override suspend fun changeLineSize(lineSize: Int) {
        dataStore.edit { preferences ->
            preferences[WIDTH_LINE_SIZE] = lineSize
        }
    }

}