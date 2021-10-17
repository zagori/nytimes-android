package io.github.zagori.nytimes.di.modules

import dagger.Module
import dagger.Provides
import io.github.zagori.nytimes.source.local.NYTDatabase
import io.github.zagori.nytimes.source.local.daos.ArticlesDao
import javax.inject.Singleton

@Module
object DaoModule {
    @Provides
    @Singleton
    fun provideArticlesDao(db: NYTDatabase): ArticlesDao = db.getArticlesDao()

    // Provide other daos in here...
}