package tech.purplebeen.gitissue.feature.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import tech.purplebeen.gitissue.BaseApplication
import tech.purplebeen.gitissue.R
import tech.purplebeen.gitissue.databinding.ActivityMainBinding
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectComponent()
        initDatabinding()
        initRecyclerView()
        observeViewModel()

        viewModel.getRepoList("google", "dagger")
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

    private fun initRecyclerView() {
        binding.recyclerview.adapter = issueAdapter
    }

    private fun observeViewModel() {
        viewModel.issueUpdateEvent.observe(this) {
            issueAdapter.submitList(viewModel.itemList)
        }
    }
}