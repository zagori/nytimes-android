package io.github.zagori.nytimes.di.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object ContextModule {
    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application
}