package com.kldaji.loanclientmanagement.model.data

import android.net.Uri
import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

@ProvidedTypeConverter
class UriListTypeConverter {
    @TypeConverter
    fun uriListToJson(uriList: List<Uri>): String {
        val json = JSONObject()
        val jsonArray = JSONArray()
        uriList.forEach { uri -> jsonArray.put(uri.toString()) }
        try {
            json.put("uriList", jsonArray)
        } catch (e: JSONException) {
            println(e.message)
        }
        return json.toString()
    }

    @TypeConverter
    fun jsonToUriList(json: String): List<Uri> {
        val uriList = mutableListOf<Uri>()
        try {
            val jsonArray = JSONObject(json).get("uriList") as JSONArray
            for (i in 0 until jsonArray.length()) {
                uriList.add(Uri.parse((jsonArray.get(i) as String).replace("\\", "")))
            }
        } catch (e: JSONException) {
            println(e.message)
        }
        return uriList
    }
}
