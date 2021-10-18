package io.github.zagori.nytimes

import io.github.zagori.nytimes.models.*
import io.github.zagori.nytimes.repositories.ArticlesRepository
import io.github.zagori.nytimes.source.local.daos.ArticlesDao
import io.github.zagori.nytimes.source.remote.Endpoints
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.exceptions.base.MockitoException
import org.mockito.junit.MockitoJUnitRunner
import kotlin.random.Random

@RunWith(MockitoJUnitRunner::class)
open class TestSetup {

    @Mock
    lateinit var endpoints: Endpoints

    @Mock
    lateinit var articlesDao: ArticlesDao

    lateinit var articlesRepository: ArticlesRepository

    @Before
    fun setUp() {
        articlesRepository = ArticlesRepository(endpoints, articlesDao)
    }

    fun onSearchArticles_fromApi_success() {
        Mockito.`when`(
            endpoints.searchArticles("query", 1, API_KEY)
        ).thenReturn(
            Single.just(ApiResponse("OK","copyright",2,null,
                DocResponse(docs = getListOfDocs(1, 2)),null))
        )
    }

    fun onSearchArticles_fromApi_serverError() {
        Mockito.`when`(
            endpoints.searchArticles("query", 1, API_KEY)
        ).thenReturn(
            Single.just(ApiResponse("Error","copyright",2,null,
                null, listOf("page: must be less than or equal to 200")))
        )
    }

    fun onSearchArticles_fromApi_exception() {
        Mockito.`when`(endpoints.searchArticles("query", 1, API_KEY))
            .thenThrow(MockitoException("Something went wrong"))
    }

    fun onClearAllSearchArticles_fromLocal_success() {
        Mockito.`when`(
            articlesDao.clearSearchedArticles()
        ).thenReturn(
            Completable.complete()
        )
    }

    fun onInsertSearchArticles_toLocal_success() {
        Mockito.`when`(
            articlesDao.insertSearchedArticles(getListOfDocs())
        ).thenReturn(
            Completable.complete()
        )
    }

    fun onLoadSearchArticles_fromLocal_success() {
        Mockito.`when`(
            articlesDao.getSearchedArticles()
        ).thenReturn(
            Flowable.just(getListOfDocs())
        )
    }

    fun getListOfArticles(page: Int = 1, count: Int = 1, type: String): List<Article> {
        val list = mutableListOf<Article>()

        for (i in 1..count) {
            list.add(
                Article(
                    id = Random.nextLong(1, 100) + page+i,
                    url = "image.com/a$page$i.png",
                    section = "section$page$i",
                    byline = "byline$page$i",
                    title = "title$page$i",
                    subtitle = "subtitle$page$i",
                    publishedDate = "publishedDate$page$i",
                    media = null,
                    popularAs = type
                )
            )
        }

        return list
    }

    fun getListOfDocs(page: Int = 1, count: Int = 1): List<Doc> {
        val list = mutableListOf<Doc>()

        for (i in 1..count) {
            list.add(
                Doc(
                    _id = "$page$i",
                    multimedia = listOf(Multimedia("image.com/a1.png")),
                    headline = Headline("headline$page$i"),
                    pubDate = "pubDate$page$i",
                    subtitle = "subtitle$page$i",
                    webUrl = "webUrl$page$i"
                )
            )
        }

        return list
    }

    companion object {
        const val API_KEY = "api-key"
    }
}