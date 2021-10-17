package io.github.zagori.nytimes.di.modules

import dagger.Module
import dagger.Provides
import io.github.zagori.nytimes.repositories.ArticlesRepository
import io.github.zagori.nytimes.source.local.daos.ArticlesDao
import io.github.zagori.nytimes.source.remote.Endpoints
import javax.inject.Singleton

@Module
object RepositoryModule {

    @Provides
    @Singleton
    fun provideImagesRepository(
        endpoints: Endpoints, articlesDao: ArticlesDao
    ) = ArticlesRepository(endpoints, articlesDao)
}