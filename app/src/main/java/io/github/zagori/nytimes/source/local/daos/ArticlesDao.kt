package io.github.zagori.nytimes.source.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.github.zagori.nytimes.models.Article
import io.github.zagori.nytimes.models.Doc
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

@Dao
abstract class ArticlesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertPopularArticles(articles: List<Article>): Completable

    @Query("SELECT * FROM popular_table WHERE popularAs == :type")
    abstract fun getPopularArticles(type: String?): Flowable<List<Article>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertSearchedArticles(docs: List<Doc>): Completable

    @Query("DELETE FROM search_table")
    abstract fun clearSearchedArticles(): Completable

    @Query("SELECT * FROM search_table")
    abstract fun getSearchedArticles(): Flowable<List<Doc>>
}