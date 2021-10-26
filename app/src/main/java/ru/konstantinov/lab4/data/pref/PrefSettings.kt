package ru.konstantinov.lab4.data.pref

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

object PrefKey {
    val PREF_KEY_LAST_MENU_ID = intPreferencesKey("PREF_KEY_START_PAGE")
}