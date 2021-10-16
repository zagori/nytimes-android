package io.github.zagori.nytimes.di.components

import dagger.Component
import io.github.zagori.nytimes.di.modules.CompositeDisposableModule
import io.github.zagori.nytimes.di.scopes.CoreScope
import io.github.zagori.nytimes.viewmodels.ArticlesViewModel

@Component(
    dependencies = [CoreComponent::class],
    modules = [CompositeDisposableModule::class]
)
@CoreScope
interface ViewModelComponent {

    @Component.Factory
    interface Factory{
        fun create(coreComponent: CoreComponent): ViewModelComponent
    }

    fun inject(articlesViewModel: ArticlesViewModel)

    // Inject more viewModel in here ...
}