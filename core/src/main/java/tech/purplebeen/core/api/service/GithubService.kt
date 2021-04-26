package tech.purplebeen.core.api.service

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import tech.purplebeen.core.db.Issue

interface GithubService {
    @GET("/repos/{org}/{repo}/issues")
    fun getIssue(@Path("org") org: String, @Path("repo") repo: String): Single<List<Issue>>
}