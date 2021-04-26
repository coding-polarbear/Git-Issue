package tech.purplebeen.gitissue

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import tech.purplebeen.gitissue.databinding.DialogRepoInsertBinding

class RepoInsertDialog(context: Context,  val okButtonListener: (orgName: String, repoName: String) -> Unit ): AlertDialog(context) {
    val binding: DialogRepoInsertBinding by lazy {
        DialogRepoInsertBinding.inflate(LayoutInflater.from(context),null, false)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
        binding.okButton.setOnClickListener {
            okButtonListener(binding.editOrganization.text.toString(),
            binding.editRepo.text.toString())
            binding.editOrganization.setText("")
            binding.editRepo.setText("")
        }

    }
}