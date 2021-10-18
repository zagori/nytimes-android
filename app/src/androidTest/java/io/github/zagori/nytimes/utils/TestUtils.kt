package io.github.zagori.nytimes.utils

import io.github.zagori.nytimes.models.Article
import io.github.zagori.nytimes.models.Doc
import io.github.zagori.nytimes.models.Headline
import io.github.zagori.nytimes.models.Multimedia
import kotlin.random.Random

object TestUtils {

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
}