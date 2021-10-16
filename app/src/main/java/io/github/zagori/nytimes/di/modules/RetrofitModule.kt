package io.github.zagori.nytimes.di.modules

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import io.github.zagori.nytimes.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object RetrofitModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(
            OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
                // Logs request and response for dev flavor only
                if (BuildConfig.FLAVOR != "prod") level = HttpLoggingInterceptor.Level.BODY
            }).build()
        )
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()
}