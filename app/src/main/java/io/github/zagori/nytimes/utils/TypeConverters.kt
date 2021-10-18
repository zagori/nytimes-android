package io.github.zagori.nytimes.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.github.zagori.nytimes.models.Media
import io.github.zagori.nytimes.models.Multimedia

class MediaConverter{
    @TypeConverter
    fun fromMedia(media: List<Media>?): String = Gson().toJson(media)

    @TypeConverter
    fun toMedia(json: String): List<Media>? = Gson()
        .fromJson(json, object : TypeToken<List<Media>>(){}.type)
}

class MultimediaConverter{
    @TypeConverter
    fun fromMultimedia(media: List<Multimedia>?): String = Gson().toJson(media)

    @TypeConverter
    fun toMultimedia(json: String): List<Multimedia>? = Gson()
        .fromJson(json, object : TypeToken<List<Multimedia>>(){}.type)
}