package io.github.zagori.nytimes.models

import androidx.room.Embedded
import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(tableName = "search_table", primaryKeys = ["_id"])
data class Doc(
    val _id: String,
    val multimedia: List<Multimedia>,
    @Embedded val headline: Headline,
    @SerializedName("pub_date") val pubDate: String,
    @SerializedName("abstract") val subtitle: String,
    @SerializedName("web_url") val webUrl: String
)

data class Headline(
    @SerializedName("main") val headline: String
)

data class Multimedia(
    @SerializedName("url") val imageUrl: String
)
