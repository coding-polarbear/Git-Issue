package tech.purplebeen.core.module

import dagger.Module
import dagger.Provides
import tech.purplebeen.core.annotation.qualifier.ForRemote
import tech.purplebeen.core.annotation.qualifier.ForRepository
import tech.purplebeen.core.api.datasource.RemoteDataSource
import tech.purplebeen.core.api.repository.IssueRepository
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    @ForRepository
    fun provideIssueRepository(@ForRemote remoteDataSource: RemoteDataSource): IssueRepository {
        return IssueRepository(remoteDataSource)
    }
}