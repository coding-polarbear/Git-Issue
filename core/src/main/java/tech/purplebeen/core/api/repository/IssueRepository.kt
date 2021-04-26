package tech.purplebeen.core.api.repository

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import tech.purplebeen.core.api.datasource.DataSource
import tech.purplebeen.core.api.datasource.LocalDataSource
import tech.purplebeen.core.api.datasource.RemoteDataSource
import tech.purplebeen.core.db.Issue
import javax.inject.Inject

class IssueRepository @Inject constructor(
    val remoteDataSource: RemoteDataSource,
    val localDataSource: LocalDataSource
) : DataSource {
    override fun getIssueList(orgName: String, repoName: String): Single<List<Issue>> {
        return remoteDataSource.getIssueList(orgName, repoName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}