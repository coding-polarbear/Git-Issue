package tech.purplebeen.core.api.datasource

import io.reactivex.Observable
import io.reactivex.Single
import tech.purplebeen.core.db.Issue

interface DataSource {
    fun getIssueList(orgName: String, repoName: String): Single<List<Issue>>
}