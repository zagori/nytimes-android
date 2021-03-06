package io.github.zagori.nytimes.di.modules

import dagger.Module
import dagger.Provides
import io.github.zagori.nytimes.source.remote.Endpoints
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
object EndpointModule {

    @Provides
    @Singleton
    fun provideEndpoints(retrofit: Retrofit): Endpoints = retrofit.create(Endpoints::class.java)
}