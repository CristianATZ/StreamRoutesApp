package net.streamroutes.sreamroutesapp.core.data.datastore

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey

object PreferenceKeys {
    // Apariencia
    val THEME = booleanPreferencesKey("theme")
    val DYNAMIC_THEME = booleanPreferencesKey("dynamic_theme")

    // Mapa
    val MAP_THEME = booleanPreferencesKey("map_theme")
    val ROUTE_COLOR = intPreferencesKey("route_color")
    val NEAR_STOP_COLOR = intPreferencesKey("near_stop_color")
    val WIDTH_LINE_SIZE = intPreferencesKey("width_line_size")
}