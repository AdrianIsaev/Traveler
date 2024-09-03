package com.example.traveler.data.core

import android.content.Context
import android.content.SharedPreferences

interface AccessToMainSharedPreferencesInterface {

    fun getMainSharedPreferencesEditor(context: Context): SharedPreferences.Editor {
        return context.getSharedPreferences("NAME", Context.MODE_PRIVATE).edit()
    }
    fun getMainSharedPreferences(context: Context): SharedPreferences{
        return context.getSharedPreferences("NAME", Context.MODE_PRIVATE)
    }
}