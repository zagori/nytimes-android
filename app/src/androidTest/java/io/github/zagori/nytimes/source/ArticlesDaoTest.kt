package io.github.zagori.nytimes.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.github.zagori.nytimes.models.ListType
import io.github.zagori.nytimes.source.local.NYTDatabase
import io.github.zagori.nytimes.source.local.daos.ArticlesDao
import io.github.zagori.nytimes.utils.TestUtils
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class ArticlesDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var articlesDao: ArticlesDao
    private lateinit var db: NYTDatabase

    @Before
    fun createDb() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            NYTDatabase::class.java
        ).allowMainThreadQueries().build()

        articlesDao = db.getArticlesDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun writeAndReadToPopularTable() {
        val type = ListType.Viewed.name

        // Make sure popular_table is empty
        articlesDao.getPopularArticles(type).test().assertValue { it.isEmpty() }

        // Insert a list A, and then make sure insertion is complete and read correctly
        val articleListA = TestUtils.getListOfArticles(1, 2, type)
        articlesDao.insertPopularArticles(articleListA).test().assertComplete()
        articlesDao.getPopularArticles(type).test().assertValue {
            it[1].id == articleListA[1].id
        }

        // insert another list of items, and then make sure items are inserted
        val articleListB = TestUtils.getListOfArticles(2, 2, type)
        articlesDao.insertPopularArticles(articleListB).test().assertComplete()
        articlesDao.getPopularArticles(type).test().assertValue {
            it[2].url == articleListB[0].url
        }
    }

    @Test
    fun writeAndReadToSearchTable() {
        // Make sure search_table is empty
        articlesDao.getSearchedArticles().test().assertValue { it.isEmpty() }

        // Insert a list A, and then make sure insertion is complete and read correctly
        val docListA = TestUtils.getListOfDocs(1, 2)
        articlesDao.insertSearchedArticles(docListA).test().assertComplete()
        articlesDao.getSearchedArticles().test().assertValue {
            it[1]._id == docListA[1]._id
        }

        // insert another list of items, and then make sure items are inserted and read
        val docListB = TestUtils.getListOfDocs(2, 2)
        articlesDao.insertSearchedArticles(docListB).test().assertComplete()
        articlesDao.getSearchedArticles().test().assertValue {
            it[2].headline.headline == docListB[0].headline.headline
        }

        // Clear the table, and then make sure clearing is complete and table is empty
        articlesDao.clearSearchedArticles().test().assertComplete()
        articlesDao.getSearchedArticles().test().assertValue {
            it.isEmpty()
        }
    }
}