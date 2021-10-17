package io.github.zagori.nytimes.source.remote

import io.github.zagori.nytimes.models.ApiResponse
import io.github.zagori.nytimes.models.Article
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Endpoints {

    @GET("mostpopular/v2/{type}/{period}.json")
    fun loadMostPopular(
        @Path("type") listType: String,
        @Path("period") period: Int,
        @Query("api-key") apiKey: String
    ): Single<ApiResponse<List<Article>>>
}