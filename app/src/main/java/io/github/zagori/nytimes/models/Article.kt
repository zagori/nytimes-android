package io.github.zagori.nytimes.models

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(tableName = "popular_table", primaryKeys = ["id"])
data class Article(
    val url: String,
    val id: Long,
    val section: String,
    val byline: String,
    val title: String,
    @SerializedName("published_date") val publishedDate: String,
    @SerializedName("abstract") val subtitle: String,
    var popularAs: String
)
