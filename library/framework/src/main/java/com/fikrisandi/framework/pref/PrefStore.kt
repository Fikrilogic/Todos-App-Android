package com.fikrisandi.framework.pref

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

class PrefStore(context: Context, filename: String) {
    private val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = filename)
    var datastore = context.datastore

    suspend fun <T> read(key: String, defaultValue: T): T {
        val preferences = datastore.data.first()
        return when (defaultValue) {
            is String -> preferences[stringPreferencesKey(key)] as T ?: defaultValue
            is Int -> preferences[intPreferencesKey(key)] as T ?: defaultValue
            is Boolean -> preferences[booleanPreferencesKey(key)] as T ?: defaultValue
            is Long -> preferences[longPreferencesKey(key)] as T ?: defaultValue
            is Double -> preferences[doublePreferencesKey(key)] as T ?: defaultValue
            is Float -> preferences[floatPreferencesKey(key)] as T ?: defaultValue
            else -> defaultValue
        }
    }

    suspend fun <T> write(key: String, value: T) {
        when (value) {
            is String -> datastore.edit { it[stringPreferencesKey(key)] = value }
            is Int -> datastore.edit { it[intPreferencesKey(key)] = value }
            is Boolean -> datastore.edit { it[booleanPreferencesKey(key)] = value }
            is Long -> datastore.edit { it[longPreferencesKey(key)] = value }
            is Double -> datastore.edit { it[doublePreferencesKey(key)] = value }
            is Float -> datastore.edit { it[floatPreferencesKey(key)] = value }
            else -> Unit
        }
    }

    suspend fun clearAll() {
        datastore.edit { preferences ->
            preferences.clear()
        }
    }

}