package tech.purplebeen.gitissue

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.WindowManager
import tech.purplebeen.gitissue.databinding.DialogRepoInsertBinding

class RepoInsertDialog(context: Context,  val okButtonListener: (orgName: String, repoName: String) -> Unit ): AlertDialog(context) {
    val binding: DialogRepoInsertBinding by lazy {
        DialogRepoInsertBinding.inflate(LayoutInflater.from(context),null, false)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
        window?.clearFlags( WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM)
        window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        binding.okButton.setOnClickListener {
            okButtonListener(binding.editOrganization.text.toString(),
            binding.editRepo.text.toString())
            binding.editOrganization.setText("")
            binding.editRepo.setText("")
        }

    }
}