package io.github.zagori.nytimes.source.remote

import io.github.zagori.nytimes.models.ApiResponse
import io.github.zagori.nytimes.models.Article
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Endpoints {

    @GET("mostpopular/v2/viewed/{period}.json")
    fun loadMostViewed(
        @Path("period") period: Int,
        @Query("api-key") apiKey: String
    ): Single<ApiResponse<List<Article>>>

    @GET("mostpopular/v2/shared/{period}.json")
    fun loadMostShared(
        @Path("period") period: Int,
        @Query("api-key") apiKey: String
    ): Single<ApiResponse<List<Article>>>

    @GET("mostpopular/v2/emailed/{period}.json")
    fun loadMostEmailed(
        @Path("period") period: Int,
        @Query("api-key") apiKey: String
    ): Single<ApiResponse<List<Article>>>
}