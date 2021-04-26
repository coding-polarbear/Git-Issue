package tech.purplebeen.gitissue.feature.main

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import tech.purplebeen.core.annotation.qualifier.ForRepository
import tech.purplebeen.core.api.repository.IssueRepository
import tech.purplebeen.gitissue.mvvm.SingleLiveEvent
import tech.purplebeen.model.Issue
import javax.inject.Inject

class MainViewModel @Inject constructor(
    application: Application,
    @ForRepository val repository: IssueRepository
) : AndroidViewModel(application) {
    companion object {
        val TAG: String = MainViewModel::class.java.simpleName
    }

    private val _issueListUpdateEvent: SingleLiveEvent<Void> = SingleLiveEvent()
    val issueUpdateEvent: SingleLiveEvent<Void>
        get() = _issueListUpdateEvent

    private val _issueErrorEvent: SingleLiveEvent<Void> = SingleLiveEvent()
    val issueErrorEvent: SingleLiveEvent<Void>
        get() = _issueErrorEvent

    private val _issueList: ArrayList<Issue> = ArrayList()
    val issueList: List<Issue>
        get() = _issueList

    @SuppressLint("CheckResult")
    fun getRepoList(org: String, repo: String) {
        repository.getIssueList(org, repo)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({res ->
                _issueList.addAll(res)
                issueUpdateEvent.call()
            }, {e ->
                e.message?.let {
                    Log.e(TAG, it)
                }

            })
    }
}