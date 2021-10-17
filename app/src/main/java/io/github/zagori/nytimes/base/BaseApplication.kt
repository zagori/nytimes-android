package io.github.zagori.nytimes.base

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.util.CoilUtils
import io.github.zagori.nytimes.R
import io.github.zagori.nytimes.di.components.CoreComponent
import io.github.zagori.nytimes.di.components.DaggerCoreComponent
import okhttp3.OkHttpClient

class BaseApplication : Application(), ImageLoaderFactory{

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerCoreComponent.factory().create(this)
    }

    // set the singleton ImageLoader instance
    override fun newImageLoader(): ImageLoader = ImageLoader
        .Builder(applicationContext)
        .crossfade(true)
        .placeholder(R.color.teal_700)
        .error(R.color.design_default_color_error)
        .fallback(R.color.design_default_color_error)
        .okHttpClient {
            OkHttpClient.Builder().cache(
                CoilUtils.createDefaultCache(applicationContext)
            ).build()
        }.build()

    companion object {
        @JvmStatic
        var appComponent: CoreComponent? = null
    }
}