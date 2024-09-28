package net.apw.codeaurora.gitclone;

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore(name = "settings")

object SettingsPreferenceKeys {
    val DARK_MODE = booleanPreferencesKey("dark_mode")
}

suspend fun saveDarkModeSetting(context: Context, isEnabled: Boolean) {
    context.dataStore.edit { settings ->
        settings[SettingsPreferenceKeys.DARK_MODE] = isEnabled
    }
}

fun getDarkModeSetting(context: Context): Flow<Boolean> {
    return context.dataStore.data.map { preferences ->
        preferences[SettingsPreferenceKeys.DARK_MODE] ?: false
    }
}