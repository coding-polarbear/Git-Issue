package tech.purplebeen.gitissue.feature.issue_detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import tech.purplebeen.gitissue.BaseApplication
import tech.purplebeen.gitissue.R
import tech.purplebeen.gitissue.databinding.ActivityIssueDetailBinding
import javax.inject.Inject

class IssueDetailActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    val binding: ActivityIssueDetailBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_issue_detail)
    }

    val viewModel: IssueDetailViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[IssueDetailViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectComponent()
        binding.viewModel = viewModel
        viewModel.initDataFromIntent(intent)
        supportActionBar?.title = viewModel.titleText
    }

    fun injectComponent() {
        BaseApplication.getAppComponent()
            .issueDetailComponentBuilder()
            .activity(this)
            .build()
            .inject(this)
    }
}