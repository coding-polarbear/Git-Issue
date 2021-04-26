package tech.purplebeen.core.api.datasource

import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import tech.purplebeen.core.api.service.GithubService
import tech.purplebeen.core.db.Issue

class RemoteDataSource (retrofit: Retrofit): DataSource {
    private val githubService: GithubService = retrofit.create(GithubService::class.java)

    override fun getIssueList(orgName: String, repoName: String):Single<List<Issue>> {
        return githubService
            .getIssue(orgName, repoName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}