package tech.purplebeen.gitissue.feature.issue_detail

import android.app.Application
import android.content.Intent
import androidx.databinding.Observable
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import tech.purplebeen.gitissue.R
import tech.purplebeen.gitissue.util.GlobalConst
import javax.inject.Inject

class IssueDetailViewModel @Inject constructor(application: Application): AndroidViewModel(application) {
    val userNameField: ObservableField<String> = ObservableField()
    val bodyField: ObservableField<String> = ObservableField()
    val profileUrlField: ObservableField<String> = ObservableField()
    var titleText: String = getApplication<Application>().resources.getString(R.string.app_name)

    fun initDataFromIntent(intent: Intent?) {
        intent?.let {
            userNameField.set(it.getStringExtra(GlobalConst.USER_NAME))
            bodyField.set(it.getStringExtra(GlobalConst.BODY))
            profileUrlField.set(it.getStringExtra(GlobalConst.PROFILE_URL))
            val issueNumber = it.getIntExtra(GlobalConst.ISSUE_NUMBER, -1)
            if(issueNumber != -1) {
                titleText = "#$issueNumber"
            }
        }
    }
}