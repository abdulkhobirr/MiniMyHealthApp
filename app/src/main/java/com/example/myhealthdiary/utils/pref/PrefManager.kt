package com.example.myhealthdiary.utils.pref

interface PrefManager {
    fun getBoolean(key: String, default: Boolean = false): Boolean
    fun saveBoolean(key: String, value: Boolean)

    fun getString(key: String, default: String = ""): String
    fun saveString(key: String, value: String)

    fun removeKey(key: String)
}