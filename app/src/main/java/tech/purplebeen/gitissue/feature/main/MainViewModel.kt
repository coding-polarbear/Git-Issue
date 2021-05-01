package tech.purplebeen.gitissue.feature.main

import android.annotation.SuppressLint
import android.app.Application
import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import tech.purplebeen.core.Item
import tech.purplebeen.core.annotation.qualifier.ForRepository
import tech.purplebeen.core.api.repository.IssueRepository
import tech.purplebeen.gitissue.mvvm.SingleLiveEvent
import tech.purplebeen.core.MainViewType
import tech.purplebeen.core.annotation.qualifier.ForPreference
import tech.purplebeen.core.db.Issue
import tech.purplebeen.gitissue.R
import tech.purplebeen.gitissue.util.GlobalConst
import javax.inject.Inject

class MainViewModel @Inject constructor(
    application: Application,
    @ForPreference val sharedPreferences: SharedPreferences,
    @ForRepository val repository: IssueRepository
) : AndroidViewModel(application) {
    companion object {
        val TAG: String = MainViewModel::class.java.simpleName

        private val SUCCESS_ORG_NAME = "success_org_name"
        private val SUCCESS_REPO_NAME = "success_repo_name"
    }

    private val _issueListUpdateEvent: SingleLiveEvent<Void> = SingleLiveEvent()
    val issueUpdateEvent: SingleLiveEvent<Void>
        get() = _issueListUpdateEvent

    private val _issueErrorEvent: SingleLiveEvent<Void> = SingleLiveEvent()
    val issueErrorEvent: SingleLiveEvent<Void>
        get() = _issueErrorEvent

    private val _itemList: ArrayList<Item> = ArrayList()
    val itemList: List<Item>
        get() = _itemList

    private val _titleField: ObservableField<String> = ObservableField()
    val titleField: ObservableField<String>
        get() = _titleField

    private val _titleClickEvent: SingleLiveEvent<Void> = SingleLiveEvent()
    val titleClickEvent: SingleLiveEvent<Void>
        get() = _titleClickEvent

    var selectedIssue: Issue? = null

    private val _issueSelectedEvent: SingleLiveEvent<Void> = SingleLiveEvent()
    val issueSelectedEvent: SingleLiveEvent<Void>
        get() = _issueSelectedEvent

    private val _imageSelectEvent: SingleLiveEvent<Void> = SingleLiveEvent()
    val imageSelectEvent: SingleLiveEvent<Void>
        get() = _imageSelectEvent

    @SuppressLint("CheckResult")
    fun getRepoList(org: String, repo: String) {
        val before = titleField.get()
        titleField.set("$org / $repo")
        repository.getIssueList(org, repo)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({res ->
                _itemList.clear()
                _itemList.add(Item(MainViewType.TITLE, null))
                for(issue in res) {
                    _itemList.add(Item(MainViewType.ISSUE, issue))
                }
                _itemList.add(5, Item(MainViewType.IMAGE, null))

                sharedPreferences.edit().putString(SUCCESS_ORG_NAME, org).apply()
                sharedPreferences.edit().putString(SUCCESS_REPO_NAME, repo).apply()
                issueUpdateEvent.call()
            }, {e ->
                e.message?.let {
                    Log.e(TAG, it)
                }
                _titleField.set(before)
                _issueErrorEvent.call()
            })
    }

    fun onTitleClicked() {
        _titleClickEvent.call()
    }

    fun onIssueClicked(id: Int) {
        selectedIssue = _itemList.filter { item ->
            item.issue?.id == id
        }[0].issue

        _issueSelectedEvent.call()
    }

    fun onImageClicked() {
        _imageSelectEvent.call()
    }

    fun parseDataFromIntent(dataString: String?) {
        dataString?.let{
            val dataArray = it.split("/")
            val orgs = dataArray[dataArray.size - 2]
            val repo = dataArray[dataArray.size - 1]
            getRepoList(orgs, repo)
        }
    }

    fun loadData(intent: Intent) {
        if(intent.dataString == null) {
            val lastOrg = sharedPreferences.getString(SUCCESS_ORG_NAME, "")
            val lastRepo = sharedPreferences.getString(SUCCESS_REPO_NAME, "")
            if (!lastOrg.isNullOrEmpty() && !lastRepo.isNullOrEmpty()) {
                getRepoList(lastOrg, lastRepo)
            } else {
                getRepoList("google", "dagger")
            }
        } else {
            parseDataFromIntent(intent.dataString)
        }
    }
}