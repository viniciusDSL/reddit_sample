package com.vinidsl.redditsample.storage

import android.content.Context
import android.content.SharedPreferences

object SharedPreferencesManager {

    private var sharedPref: SharedPreferences? = null

    private fun instancePrefIfNeed(context: Context) {
       if (sharedPref == null) {
           sharedPref = context.getSharedPreferences(
               "entries_status", Context.MODE_PRIVATE
           )
       }
    }

    fun verifyRedditEntry(context: Context, entryName: String): Boolean {
        instancePrefIfNeed(context)
        return sharedPref!!.getBoolean(entryName, false)
    }

    fun updateRedditEntryStatus(context: Context, entryName: String?) {
        entryName?.let {
            instancePrefIfNeed(context)
            with(sharedPref!!.edit()) {
                putBoolean(it, true)
                apply()
            }
        }
    }
}