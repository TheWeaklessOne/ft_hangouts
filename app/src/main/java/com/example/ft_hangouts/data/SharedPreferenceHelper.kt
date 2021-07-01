package com.example.ft_hangouts.data

import android.content.Context

/**
 * Your last visit was on:
    11:05 PM, 12.11.2021
 */


/**
 * Wrapper for shared preferences
 *
 * @param context interface to global information about an application environment
 */
class SharedPreferenceHelper(private val context: Context) {

    private val prefs = context.getSharedPreferences(FT_HANGOUTS_PREFS, Context.MODE_PRIVATE)

    /**
     * Date when user turned app to background or closed it
     */
    var backgroundDate: String
        get() = prefs.getString(FT_HANGOUTS_KEY, "").orEmpty()
        set(value) = prefs.edit().putString(FT_HANGOUTS_KEY, value).apply()

    companion object {
        private const val FT_HANGOUTS_PREFS = "FtHangoutsPrefs"
        private const val FT_HANGOUTS_KEY = "FtHangoutsKey"
    }
}