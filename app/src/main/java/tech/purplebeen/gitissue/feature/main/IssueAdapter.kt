package tech.purplebeen.gitissue.feature.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tech.purplebeen.gitissue.databinding.ItemDefaultImageBinding
import tech.purplebeen.gitissue.databinding.ItemDefaultIssueBinding
import tech.purplebeen.gitissue.databinding.ItemDefaultTitleBinding
import tech.purplebeen.gitissue.util.GlobalConst
import tech.purplebeen.model.Item
import tech.purplebeen.model.MainViewType

class IssueAdapter(val viewModel: MainViewModel): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    private var itemList: List<Item> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            MainViewType.IMAGE.type -> {
                val binding: ItemDefaultImageBinding = ItemDefaultImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ImageViewHolder(binding)
            }
            MainViewType.TITLE.type -> {
                val binding: ItemDefaultTitleBinding = ItemDefaultTitleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                TitleViewHolder(binding)
            }
            else -> {
                val binding = ItemDefaultIssueBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                IssueViewHolder(binding)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return itemList[position].viewType.type
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(getItemViewType(position) == MainViewType.ISSUE.type) {
            (holder as IssueViewHolder).binding.issue = itemList[position].issue
        } else if(getItemViewType(position) == MainViewType.TITLE.type) {
            (holder as TitleViewHolder).binding.viewModel = viewModel
        } else {
            (holder as ImageViewHolder).binding.imageUrl = GlobalConst.IMAGE_URL
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun submitList(target: List<Item>) {
        this.itemList = target
        notifyDataSetChanged()
    }
}