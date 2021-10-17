package io.github.zagori.nytimes.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.github.zagori.nytimes.models.Article
import io.github.zagori.nytimes.source.local.daos.ArticlesDao
import io.github.zagori.nytimes.utils.MediaConverter

@Database(
    entities = [Article::class],
    version = 1, exportSchema = false
)
@TypeConverters(MediaConverter::class)
abstract class NYTDatabase : RoomDatabase() {
    abstract fun getArticlesDao(): ArticlesDao

    companion object {
        const val DB_NAME = "ny_times_db"
    }
}