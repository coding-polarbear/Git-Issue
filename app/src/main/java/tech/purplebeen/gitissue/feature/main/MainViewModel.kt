package tech.purplebeen.gitissue.feature.main

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import tech.purplebeen.core.annotation.qualifier.ForRepository
import tech.purplebeen.core.api.repository.IssueRepository
import tech.purplebeen.gitissue.mvvm.SingleLiveEvent
import tech.purplebeen.model.Item
import tech.purplebeen.model.MainViewType
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

    private val _itemList: ArrayList<Item> = ArrayList()
    val itemList: List<Item>
        get() = _itemList

    private val _titleField: ObservableField<String> = ObservableField()
    val titleField: ObservableField<String>
        get() = _titleField

    private val _titleClickEvent: SingleLiveEvent<Void> = SingleLiveEvent()
    val titleClickEvent: SingleLiveEvent<Void>
        get() = _titleClickEvent

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
}