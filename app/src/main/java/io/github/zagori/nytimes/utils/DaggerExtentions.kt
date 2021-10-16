package io.github.zagori.nytimes.utils

import io.github.zagori.nytimes.base.BaseApplication
import io.github.zagori.nytimes.di.components.DaggerViewModelComponent
import io.github.zagori.nytimes.di.components.ViewModelComponent

val viewModelInjection: ViewModelComponent
    get() = DaggerViewModelComponent.factory().create(BaseApplication.appComponent!!)