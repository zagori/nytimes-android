package io.github.zagori.nytimes.di.components

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import io.github.zagori.nytimes.di.modules.EndpointModule
import io.github.zagori.nytimes.di.modules.RepositoryModule
import io.github.zagori.nytimes.di.modules.RetrofitModule
import io.github.zagori.nytimes.repositories.ArticlesRepository
import javax.inject.Singleton

@Component(
    modules = [
        RetrofitModule::class,
        EndpointModule::class,
        RepositoryModule::class
    ]
)
@Singleton
interface CoreComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): CoreComponent
    }

    fun articlesRepository(): ArticlesRepository

    // Inject more repositories that are to be injected in the viewModels in here ...
}