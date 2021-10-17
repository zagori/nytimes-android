package io.github.zagori.nytimes.models

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(tableName = "popular_table", primaryKeys = ["id"])
data class Article(
    val id: Long,
    val url: String,
    val section: String,
    val byline: String,
    val title: String,
    @SerializedName("abstract") val subtitle: String,
    @SerializedName("published_date") val publishedDate: String,
    val media: List<Media>?,
    var popularAs: String
)

data class Media(
    @SerializedName("media-metadata") val metadata: List<Image>
)

data class Image(
    val url: String
)
