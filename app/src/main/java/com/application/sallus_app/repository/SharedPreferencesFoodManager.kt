package com.application.sallus_app.repository

import android.content.Context
import android.content.SharedPreferences
import com.application.sallus_app.model.FoodData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SharedPreferencesFoodManager(private val context: Context) {

    private val sharedPreferences: SharedPreferences by lazy {
        context.getSharedPreferences("API_DATA", Context.MODE_PRIVATE)
    }

    fun saveAPIData(apiData: String) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString("API_RESPONSE", apiData)
        editor.apply()
    }

    fun getAPIData(): String? {
        return sharedPreferences.getString("API_RESPONSE", null)
    }

    fun parseAPIData(apiData: String): List<FoodData> {
        val gson = Gson()
        val foodListType = object : TypeToken<List<FoodData>>() {}.type
        return gson.fromJson(apiData, foodListType)
    }

    fun clearAPIData() {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.remove("API_RESPONSE")
        editor.apply()
    }
}