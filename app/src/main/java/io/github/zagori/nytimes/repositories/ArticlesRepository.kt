package io.github.zagori.nytimes.repositories

import io.github.zagori.nytimes.BuildConfig
import io.github.zagori.nytimes.source.local.daos.ArticlesDao
import io.github.zagori.nytimes.source.remote.Endpoints
import io.reactivex.rxjava3.core.Completable

class ArticlesRepository(
    private val endpoints: Endpoints,
    private val articlesDao: ArticlesDao
) {

    /**
     * This will load the list of most viewed articles and save them to the local db
     * */
    fun loadAndSaveMostViewed(): Completable = endpoints
        .loadMostViewed(PERIOD, BuildConfig.API_KEY)
        .flatMapCompletable { apiResponse ->

            when(apiResponse.status){
                "OK" -> articlesDao.insertPopularArticles(apiResponse.results)
                else -> throw Throwable("No articles have been found. Try again later")
            }
        }

    companion object {
        // API static params
        const val PERIOD = 1
    }
}