package io.github.zagori.nytimes.repositories

import io.github.zagori.nytimes.BuildConfig
import io.github.zagori.nytimes.source.remote.Endpoints

class ArticlesRepository(
    private val endpoints: Endpoints
) {

    fun getRemoteMostViewed() = endpoints.loadMostViewed(PERIOD, BuildConfig.API_KEY)

    companion object {
        // API static params
        const val PERIOD = 1
    }
}