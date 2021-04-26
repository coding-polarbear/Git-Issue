package tech.purplebeen.core.module

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import tech.purplebeen.core.annotation.qualifier.ForAPI
import tech.purplebeen.core.annotation.qualifier.ForDatabase
import tech.purplebeen.core.annotation.qualifier.ForLocal
import tech.purplebeen.core.annotation.qualifier.ForRemote
import tech.purplebeen.core.api.datasource.DataSource
import tech.purplebeen.core.api.datasource.LocalDataSource
import tech.purplebeen.core.api.datasource.RemoteDataSource
import tech.purplebeen.core.db.AppDatabase
import javax.inject.Singleton

@Module
class DataSourceModule {
    @Provides
    @Singleton
    @ForRemote
    fun provideRemoteDataSource(@ForAPI retrofit: Retrofit): RemoteDataSource {
        return RemoteDataSource(retrofit)
    }

    @Provides
    @Singleton
    @ForLocal
    fun provideLocalDataSource(@ForDatabase db: AppDatabase): LocalDataSource {
        return LocalDataSource(db)
    }
}