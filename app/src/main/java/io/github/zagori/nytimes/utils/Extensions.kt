package io.github.zagori.nytimes.utils

import io.github.zagori.nytimes.base.BaseApplication
import io.github.zagori.nytimes.di.components.DaggerViewModelComponent
import io.github.zagori.nytimes.di.components.ViewModelComponent
import java.text.SimpleDateFormat
import java.util.*

/**
 * Dagger extension to inject viewModels with required dependencies
 * */
val viewModelInjection: ViewModelComponent
    get() = DaggerViewModelComponent.factory().create(BaseApplication.appComponent!!)

/**
 * Convert string to date using a pattern
 * */
fun String.toDate(pattern: String): Date? =
    SimpleDateFormat(pattern, Locale.getDefault()).parse(this)

/**
 * Convert date to string using a pattern
 * */
fun Date.format(pattern: String): String? =
    SimpleDateFormat(pattern, Locale.getDefault()).format(this)