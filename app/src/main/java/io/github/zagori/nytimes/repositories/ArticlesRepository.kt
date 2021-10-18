package io.github.zagori.nytimes.repositories

import io.github.zagori.nytimes.models.Article
import io.github.zagori.nytimes.models.Doc
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
        .loadMostPopular(listType.lowercase(), PERIOD)
        .flatMapCompletable { apiResponse ->

            when(apiResponse.status){
                "OK" -> {
                    // inject list type for each item
                    apiResponse.results?.forEach { article -> article.popularAs = listType }

                    // then insert the result to the popular table
                    apiResponse.results?.let { articlesDao.insertPopularArticles(it) }
                        ?: throw Throwable("No articles have been found. Try again later")
                }
                else -> throw Throwable("No articles have been found. Try again later")
            }
        }

    /**
     * Get a flow of most viewed articles from the local db
     **/
    fun getLocalMostViewed(listType: String?): Flowable<State<List<Article>>> = articlesDao
        .getPopularArticles(listType)
        .flatMap {
            return@flatMap Flowable.just(State.Success(it))
        }

    /**
     * This will load the list of articles using keyworks entered by user,
     * and then save them to the local db
     **/
    fun loadAndSaveBySearch(query: String, page: Int): Completable = endpoints
        .searchArticles(query, page)
        .flatMapCompletable { apiResponse ->

            when(apiResponse.status){
                "OK" -> {
                    if (apiResponse.response == null) throw Throwable("No articles have been found. Try again later")

                    // if page 1 is returned, then clear cache and push fresh data to the database
                    if (page == 1) articlesDao.clearSearchedArticles()
                        .andThen(articlesDao.insertSearchedArticles(apiResponse.response.docs))

                    // if page is not the first, then append the page data to the database
                    else articlesDao.insertSearchedArticles(apiResponse.response.docs)
                }
                else -> throw Throwable("No articles have been found. Try again later")
            }
        }

    /**
     * Get a flow of searched articles from the local db
     **/
    fun getLocalSearched(): Flowable<State<List<Doc>>> = articlesDao
        .getSearchedArticles()
        .flatMap {
            return@flatMap Flowable.just(State.Success(it))
        }

    companion object {
        // API static params
        const val PERIOD = 1
    }
}