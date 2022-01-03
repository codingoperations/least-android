package io.least.data

import android.content.SharedPreferences

class LastKnownStatePersister(
    private val preferences: SharedPreferences
) {
    suspend fun save(key: String, data: PreferencePersistable) {
        preferences.edit().putString(key, data.encode()).apply()
    }
}