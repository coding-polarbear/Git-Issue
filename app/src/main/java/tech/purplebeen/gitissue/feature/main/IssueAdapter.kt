package tech.purplebeen.gitissue.feature.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tech.purplebeen.gitissue.databinding.ItemDefaultIssueBinding
import tech.purplebeen.model.Issue

class IssueAdapter: RecyclerView.Adapter<IssueViewHolder>(){
    private var issueList: List<Issue> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IssueViewHolder {
        val binding = ItemDefaultIssueBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return IssueViewHolder(binding)
    }

    override fun onBindViewHolder(holder: IssueViewHolder, position: Int) {
        holder.binding.issue = issueList[position]
    }

    override fun getItemCount(): Int {
        return issueList.size
    }

    fun submitList(target: List<Issue>) {
        this.issueList = target
        notifyDataSetChanged()
    }
}