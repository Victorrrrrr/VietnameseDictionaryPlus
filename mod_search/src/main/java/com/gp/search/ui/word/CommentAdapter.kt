package com.gp.search.ui.word

import android.view.LayoutInflater
import android.view.ViewGroup
import com.gp.common.model.SuggestListItem
import com.gp.framework.adapter.BaseBindViewHolder
import com.gp.framework.adapter.BaseRecyclerViewAdapter
import com.gp.mod_search.databinding.ItemCommentBinding

class CommentAdapter : BaseRecyclerViewAdapter<SuggestListItem, ItemCommentBinding>() {
    override fun onBindDefViewHolder(
        holder: BaseBindViewHolder<ItemCommentBinding>,
        item: SuggestListItem?,
        position: Int
    ) {
        holder.binding.apply {
            username = item?.username
            tvComment.text = item?.content

            // 0:待处理 1:已处理 2:忽略
            tvStatus.text = when(item?.status) {
                0 -> {
                    "待处理"
                }
                1 -> {
                    "已处理"
                }
                2 -> {
                    "忽略"
                }
                else -> ""
            }


        }
    }

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): ItemCommentBinding {
        return ItemCommentBinding.inflate(layoutInflater, parent, false)
    }


}