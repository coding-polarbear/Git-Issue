package tech.purplebeen.core.api.datasource

import io.reactivex.Single
import tech.purplebeen.model.Issue

interface DataSource {
    fun getIssueList(orgName: String, repoName: String): Single<List<Issue>>
}