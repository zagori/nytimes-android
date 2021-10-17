package io.github.zagori.nytimes.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.github.zagori.nytimes.models.Media

class MediaConverter{
    @TypeConverter
    fun fromMedia(media: List<Media>?): String = Gson().toJson(media)

    @TypeConverter
    fun toMedia(json: String): List<Media>? = Gson()
        .fromJson(json, object : TypeToken<List<Media>>(){}.type)
}