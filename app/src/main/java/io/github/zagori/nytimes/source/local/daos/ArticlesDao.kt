package io.github.zagori.nytimes.source.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.github.zagori.nytimes.models.Article
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

@Dao
abstract class ArticlesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertPopularArticles(articles: List<Article>): Completable

    @Query("DELETE FROM popular_table")
    abstract fun clearAllPopular(): Completable

    @Query("SELECT * FROM popular_table")
    abstract fun getPopularArticles(): Flowable<List<Article>>
}