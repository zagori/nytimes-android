package io.github.zagori.nytimes.models

import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLException

sealed class State<out T> {

    // Loading state
    object Loading : State<Nothing>()

    // Success state
    data class Success<out T>(val data: T) : State<T>()

    // Error state
    data class Error(val throwable: Throwable) : State<Nothing>() {
        fun getMessage(): String {
            throwable.printStackTrace()
            return when (throwable) {
                is HttpException,
                is UnknownHostException,
                is SocketTimeoutException,
                is ConnectException,
                is SSLException -> "Couldn't connect to the server. Please check your internet connection and try again."
                else -> throwable.message ?: "An unexpected error has occurred. Please try again later."
            }
        }
    }
}
