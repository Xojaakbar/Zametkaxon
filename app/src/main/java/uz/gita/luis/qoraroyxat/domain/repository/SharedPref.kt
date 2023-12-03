package uz.gita.luis.qoraroyxat.domain.repository

import android.content.Context
import uz.gita.luis.qoraroyxat.app.App

class SharedPref {

    companion object {
        private val instance = SharedPref()
        private val pref = App.instance.getSharedPreferences("ContactApp", Context.MODE_PRIVATE)
        fun getInstance() = instance
    }

    var hasPassword: Boolean
        set(value) = pref.edit().putBoolean("hasPassword", value).apply()
        get() = pref.getBoolean("hasPassword", false)

    var password: String
        set(value) = pref.edit().putString("Password", value).apply()
        get() = pref.getString("Password", "").toString()

}