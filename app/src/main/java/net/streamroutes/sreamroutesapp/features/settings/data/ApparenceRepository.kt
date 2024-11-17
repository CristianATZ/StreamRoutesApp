package net.streamroutes.sreamroutesapp.features.settings.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import net.streamroutes.sreamroutesapp.core.data.datastore.PreferenceKeys.DYNAMIC_THEME
import net.streamroutes.sreamroutesapp.core.data.datastore.PreferenceKeys.THEME
import net.streamroutes.sreamroutesapp.core.data.datastore.apparenceDataStore
import javax.inject.Inject
import javax.inject.Singleton

interface ApparenceRepository {
    suspend fun changeTheme(theme: Boolean)
    suspend fun enableDynamicTheme(dynamic: Boolean)
}

@Singleton
class ApparenceRepositoryImp @Inject constructor(
    @ApplicationContext private val context: Context
) : ApparenceRepository {

    private val dataStore = context.apparenceDataStore

    // leer valor del tema
    val themeMode: Flow<Boolean> = dataStore.data
        .map { preferences ->
            preferences[THEME] ?: false // TEMA CLARO POR DEFECTO
        }

    // leer valor del tema dinamico
    val dynamicTheme: Flow<Boolean> = dataStore.data
        .map { preferences ->
            preferences[DYNAMIC_THEME] ?: false // DESACTIVADO POR DEFECTO
        }

    // cambiar tema de la aplicacion
    override suspend fun changeTheme(theme: Boolean) {
        dataStore.edit { preferences ->
            preferences[THEME] = theme
        }
    }

    override suspend fun enableDynamicTheme(dynamic: Boolean) {
        dataStore.edit { preferences ->
            preferences[DYNAMIC_THEME] = dynamic
        }
    }
}