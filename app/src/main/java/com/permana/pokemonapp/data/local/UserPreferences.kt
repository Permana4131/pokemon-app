package com.permana.pokemonapp.data.local

import android.content.Context
import android.content.SharedPreferences

class UserPreferences(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    fun saveLoginStatus(username: String) {
        prefs.edit()
            .putBoolean("is_logged_in", true)
            .putString("username", username)
            .apply()
    }

    fun isLoggedIn(): Boolean = prefs.getBoolean("is_logged_in", false)

    fun getUsername(): String? = prefs.getString("username", null)

    fun clear() {
        prefs.edit().clear().apply()
    }
}
