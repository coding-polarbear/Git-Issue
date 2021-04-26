package tech.purplebeen.core.module

import dagger.Module
import dagger.Provides
import tech.purplebeen.core.annotation.qualifier.ForLocal
import tech.purplebeen.core.annotation.qualifier.ForRemote
import tech.purplebeen.core.annotation.qualifier.ForRepository
import tech.purplebeen.core.api.datasource.LocalDataSource
import tech.purplebeen.core.api.datasource.RemoteDataSource
import tech.purplebeen.core.api.repository.IssueRepository
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    @ForRepository
    fun provideIssueRepository(@ForRemote remoteDataSource: RemoteDataSource,
    @ForLocal localDatasource: LocalDataSource): IssueRepository {
        return IssueRepository(remoteDataSource, localDatasource)
    }
}