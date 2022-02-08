package com.kldaji.loanclientmanagement.model.data

import android.net.Uri
import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

@ProvidedTypeConverter
class ImageUriListTypeConverter {
    @TypeConverter
    fun uriListToJson(imageUriList: List<ImageUri>): String {
        val imageUriListJson = JSONObject()
        val jsonArray = JSONArray()
        imageUriList.forEach {
            val imageUriJson = JSONObject()
            imageUriJson.put("id", it.id)
            imageUriJson.put("uri", it.uri.toString())
            jsonArray.put(imageUriJson)
        }
        try {
            imageUriListJson.put("imageUriList", jsonArray)
        } catch (e: JSONException) {
            println(e.message)
        }
        return imageUriListJson.toString()
    }

    @TypeConverter
    fun jsonToUriList(json: String): List<ImageUri> {
        val imageUriList = mutableListOf<ImageUri>()
        try {
            val jsonArray = JSONObject(json).get("imageUriList") as JSONArray
            for (i in 0 until jsonArray.length()) {
                val imageUriJson = jsonArray.getJSONObject(i)
                imageUriList.add(ImageUri(imageUriJson.getLong("id"),
                    Uri.parse((imageUriJson.getString("uri")).replace("\\", ""))))
            }
        } catch (e: JSONException) {
            println(e.message)
        }
        return imageUriList
    }
}
