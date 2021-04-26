package tech.purplebeen.gitissue.feature.main

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import tech.purplebeen.gitissue.BaseApplication
import tech.purplebeen.gitissue.R
import tech.purplebeen.gitissue.RepoInsertDialog
import tech.purplebeen.gitissue.databinding.ActivityMainBinding
import tech.purplebeen.gitissue.feature.issue_detail.IssueDetailActivity
import tech.purplebeen.gitissue.util.GlobalConst
import tech.purplebeen.gitissue.util.GlobalConst.BODY
import tech.purplebeen.gitissue.util.GlobalConst.ISSUE_NUMBER
import tech.purplebeen.gitissue.util.GlobalConst.PROFILE_URL
import tech.purplebeen.gitissue.util.GlobalConst.USER_NAME
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
    }

    private val issueAdapter: IssueAdapter by lazy {
        IssueAdapter(viewModel)
    }

   private lateinit var repoInsertDialog: RepoInsertDialog
   private lateinit var errorDialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectComponent()
        initDatabinding()
        initView()
        observeViewModel()

        viewModel.loadLastSuccessData()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        viewModel.parseDataFromIntent(intent?.dataString)
    }

    private fun injectComponent() {
        BaseApplication.getAppComponent()
            .mainComponentBuilder()
            .activity(this)
            .build()
            .inject(this)
    }

    private fun initDatabinding() {
        binding.viewModel = viewModel
    }

    private fun initView() {
        binding.recyclerview.adapter = issueAdapter

        repoInsertDialog = RepoInsertDialog(this) { orgName, repoName ->
            viewModel.getRepoList(orgName, repoName)
            repoInsertDialog.dismiss()
        }

        errorDialog = AlertDialog.Builder(this)
            .setMessage(resources.getString(R.string.error_occurred))
            .setPositiveButton(R.string.ok) {errorDialog, _ ->
                errorDialog.dismiss()
            }
            .create()
    }

    private fun observeViewModel() {
        viewModel.issueUpdateEvent.observe(this) {
            issueAdapter.submitList(viewModel.itemList)
        }

        viewModel.titleClickEvent.observe(this) {
            repoInsertDialog.show()
        }

        viewModel.issueErrorEvent.observe(this) {
            errorDialog.show()
        }

        viewModel.issueSelectedEvent.observe(this) {
            navigateToIssueDetail()
        }

        viewModel.imageSelectEvent.observe(this) {
            navigateToWeb()
        }
    }

    private fun navigateToIssueDetail() {
        viewModel.selectedIssue?.let {
            val issueDetailIntent: Intent = Intent(this, IssueDetailActivity::class.java)
            issueDetailIntent.putExtra(PROFILE_URL, it.user.profileUrl)
            issueDetailIntent.putExtra(BODY, it.body)
            issueDetailIntent.putExtra(USER_NAME, it.user.userName)
            issueDetailIntent.putExtra(ISSUE_NUMBER, it.number)
            startActivity(issueDetailIntent)
        }
    }

    private fun navigateToWeb() {
        val webIntent: Intent = Intent(Intent.ACTION_VIEW, Uri.parse(GlobalConst.THINGSFLOW_URL))
        startActivity(webIntent)
    }
}