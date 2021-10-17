package io.github.zagori.nytimes.di.modules

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import io.github.zagori.nytimes.source.local.NYTDatabase
import javax.inject.Singleton

@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context): NYTDatabase = Room
        .databaseBuilder(context, NYTDatabase::class.java, NYTDatabase.DB_NAME)
        .fallbackToDestructiveMigration()
        .build()
}