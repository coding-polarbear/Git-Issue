package tech.purplebeen.core.api.datasource

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import tech.purplebeen.core.db.AppDatabase
import tech.purplebeen.core.db.Issue

class LocalDataSource(val db: AppDatabase): DataSource {
    override fun getIssueList(orgName: String, repoName: String): Single<List<Issue>> {
        return db.issueDao()
            .getAll(orgName, repoName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun saveIssueList(issueList: List<Issue>): Completable {
        return db.issueDao()
            .insertAll(issueList)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}