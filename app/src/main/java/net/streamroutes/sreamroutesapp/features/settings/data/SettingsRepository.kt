package net.streamroutes.sreamroutesapp.features.settings.data

import android.content.Context
import androidx.compose.ui.graphics.Color
import javax.inject.Inject
import javax.inject.Singleton

interface SettingsRepository {
    fun changeTheme(theme: Boolean)
    fun enableDynamicTheme(dynamic: Boolean)
    fun changeMapTheme(map: Boolean)
    fun updateRouteColor(route: Color)
    fun updateNearStop(stop: Color)
}

@Singleton
class SettingsRepositoryImp @Inject constructor(
    private val context: Context
) : SettingsRepository {

    /*private val dataStore = context.dataStore

    private val COLOR_KEY = intPreferencesKey("theme_color")

    companion object {
        val THEME_MODE_KEY = booleanPreferencesKey("theme_mode") // Ejemplo de clave
    }*/


    override fun changeTheme(theme: Boolean) {
        TODO("Not yet implemented")
    }

    override fun enableDynamicTheme(dynamic: Boolean) {
        TODO("Not yet implemented")
    }

    override fun changeMapTheme(map: Boolean) {
        TODO("Not yet implemented")
    }

    override fun updateRouteColor(route: Color) {
        TODO("Not yet implemented")
    }

    override fun updateNearStop(stop: Color) {
        TODO("Not yet implemented")
    }

}