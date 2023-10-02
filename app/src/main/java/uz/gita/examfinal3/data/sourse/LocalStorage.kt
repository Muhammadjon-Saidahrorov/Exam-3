package uz.gita.examfinal3.data.sourse

import android.content.Context
import android.content.SharedPreferences

class LocalStorage (context: Context) {

    private var preferences: SharedPreferences? = null
    private var editor: SharedPreferences.Editor? = null

    init {
        preferences = context.getSharedPreferences("EXAM3", Context.MODE_PRIVATE)
        editor = preferences?.edit()
    }

    companion object {
        private var localStorage: LocalStorage? = null

        fun getInstance(): LocalStorage? {
            return localStorage
        }

        fun init(context: Context) {
            if (localStorage == null) localStorage = LocalStorage(context)
        }

    }

    fun saveEmail(str: String) {
        editor?.putString("saveEmail", str)?.apply()
    }

    fun getEmail(): String? {
        return preferences?.getString("saveEmail", "")
    }

}