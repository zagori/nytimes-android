package io.github.zagori.nytimes.base

import android.app.Application
import io.github.zagori.nytimes.di.components.CoreComponent
import io.github.zagori.nytimes.di.components.DaggerCoreComponent

class BaseApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerCoreComponent.factory().create(this)
    }

    companion object {
        @JvmStatic
        var appComponent: CoreComponent? = null
    }
}