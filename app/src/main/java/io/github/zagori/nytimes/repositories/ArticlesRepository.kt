package io.github.zagori.nytimes.repositories

import io.github.zagori.nytimes.BuildConfig
import io.github.zagori.nytimes.models.Article
import io.github.zagori.nytimes.models.State
import io.github.zagori.nytimes.source.local.daos.ArticlesDao
import io.github.zagori.nytimes.source.remote.Endpoints
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

class ArticlesRepository(
    private val endpoints: Endpoints,
    private val articlesDao: ArticlesDao
) {

    /**
     * This will load the list of most viewed articles and save them to the local db
     **/
    fun loadAndSaveMostPopular(listType: String): Completable = endpoints
        .loadMostPopular(listType.lowercase(), PERIOD, BuildConfig.API_KEY)
        .flatMapCompletable { apiResponse ->

            when(apiResponse.status){
                "OK" -> {
                    // inject list type for each item
                    apiResponse.results.forEach { article -> article.popularAs = listType }

                    // then insert the result to the popular table
                    articlesDao.insertPopularArticles(apiResponse.results)
                }
                else -> throw Throwable("No articles have been found. Try again later")
            }
        }

    /**
     * Get a flow of most viewed articles from the local db
     **/
    fun getLocalMostViewed(): Flowable<State<List<Article>>> = articlesDao.getPopularArticles()
        .flatMap {
            return@flatMap Flowable.just(State.Success(it))
        }

    companion object {
        // API static params
        const val PERIOD = 1
    }
}