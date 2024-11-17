package net.streamroutes.sreamroutesapp.core.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

val Context.apparenceDataStore: DataStore<Preferences> by preferencesDataStore(name = "apparence_preferences")
val Context.mapDataStore: DataStore<Preferences> by preferencesDataStore(name = "map_preferences")

