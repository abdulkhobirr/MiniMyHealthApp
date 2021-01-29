package com.example.myhealthdiary.utils.pref

import android.content.Context
import android.content.SharedPreferences

class PreferenceManagerImpl(
        private val context: Context,
        private val prefName: String) : PrefManager {

    private val mPreferences: SharedPreferences by lazy {
        context.getSharedPreferences(
                prefName,
                Context.MODE_PRIVATE
        )
    }

    override fun getBoolean(key: String, default: Boolean): Boolean {
        return mPreferences.getBoolean(key, default)
    }

    override fun saveBoolean(key: String, value: Boolean) {
        mPreferences.edit().putBoolean(key, value).apply()
    }

    override fun getString(key: String, default: String): String {
        return mPreferences.getString(key, default)!!
    }

    override fun saveString(key: String, value: String) {
        mPreferences.edit().putString(key, value).apply()
    }

    override fun removeKey(key: String) {
        mPreferences.edit().remove(key).apply()
    }
}