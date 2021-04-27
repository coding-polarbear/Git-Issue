package tech.purplebeen.core.api.repository

import android.util.Log
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

    companion object {
        val TAG = IssueRepository::class.java.simpleName
    }
    override fun getIssueList(orgName: String, repoName: String): Single<List<Issue>> {
        return localDataSource.getIssueList(orgName, repoName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap {
                if(it.isEmpty()) {
                    return@flatMap remoteDataSource.getIssueList(orgName, repoName)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSuccess { issueList ->
                            for(issue in issueList) {
                                issue.orgs = orgName
                                issue.repo = repoName
                            }
                            localDataSource.saveIssueList(issueList)
                                .subscribe({
                                    Log.d(TAG, "db save success")
                                }, {
                                    Log.e(TAG , "db save error")
                                })
                        }
                } else {
                    return@flatMap Single.just(it)
                }
            }
    }
}