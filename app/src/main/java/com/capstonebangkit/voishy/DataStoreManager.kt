package com.capstonebangkit.voishy

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore(name = "voice_data_store")

object DataStoreManager {
    private val KEY_CORRECT_WORDS = stringPreferencesKey("correct_words")
    private val KEY_MISSED_WORDS = stringPreferencesKey("missed_words")

    suspend fun saveCorrectWords(context: Context, correctWords: String) {
        context.dataStore.edit { settings ->
            settings[KEY_CORRECT_WORDS] = correctWords
        }
    }

    fun readCorrectWords(context: Context): Flow<String> {
        return context.dataStore.data.map { preferences ->
            preferences[KEY_CORRECT_WORDS] ?: ""
        }
    }

    suspend fun saveMissedWords(context: Context, missedWords: String) {
        context.dataStore.edit { settings ->
            settings[KEY_MISSED_WORDS] = missedWords
        }
    }

    fun readMissedWords(context: Context): Flow<String> {
        return context.dataStore.data.map { preferences ->
            preferences[KEY_MISSED_WORDS] ?: ""
        }
    }
}
