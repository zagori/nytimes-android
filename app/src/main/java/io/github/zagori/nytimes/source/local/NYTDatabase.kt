package io.github.zagori.nytimes.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.github.zagori.nytimes.models.Article
import io.github.zagori.nytimes.models.Doc
import io.github.zagori.nytimes.source.local.daos.ArticlesDao
import io.github.zagori.nytimes.utils.MediaConverter
import io.github.zagori.nytimes.utils.MultimediaConverter

@Database(
    entities = [Article::class, Doc::class],
    version = 1, exportSchema = false
)
@TypeConverters(MediaConverter::class, MultimediaConverter::class)
abstract class NYTDatabase : RoomDatabase() {
    abstract fun getArticlesDao(): ArticlesDao

    companion object {
        const val DB_NAME = "ny_times_db"
    }
}