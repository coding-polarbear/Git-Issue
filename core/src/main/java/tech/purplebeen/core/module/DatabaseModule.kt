package tech.purplebeen.core.module

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import tech.purplebeen.core.annotation.qualifier.ForApplication
import tech.purplebeen.core.annotation.qualifier.ForDatabase
import tech.purplebeen.core.db.AppDatabase
import javax.inject.Singleton

@Module
class DatabaseModule {
    companion object {
        private val DB_NAME = "git_issue.db"
    }

    @Provides
    @Singleton
    @ForDatabase
    fun provideDB(@ForApplication context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            DB_NAME
        )
            .build()
    }
}