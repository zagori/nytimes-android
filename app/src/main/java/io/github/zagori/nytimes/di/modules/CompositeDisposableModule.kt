package io.github.zagori.nytimes.di.modules

import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.disposables.CompositeDisposable

@Module
object CompositeDisposableModule {

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()
}