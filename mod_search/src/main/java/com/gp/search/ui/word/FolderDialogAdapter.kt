package com.gp.search.ui.word

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gp.common.model.FolderListBean
import com.gp.framework.adapter.BaseBindViewHolder
import com.gp.framework.adapter.BaseRecyclerViewAdapter
import com.gp.framework.ext.onClick
import com.gp.mod_search.databinding.ItemFolderBinding
import com.gp.network.manager.WordBookIdManager

class FolderDialogAdapter : BaseRecyclerViewAdapter<FolderListBean,ItemFolderBinding>() {

    private var lastIndex = -1
    private var lastWordBookId = -1

    override fun onBindDefViewHolder(
        holder: BaseBindViewHolder<ItemFolderBinding>,
        item: FolderListBean?,
        position: Int
    ) {
        if (item == null) return
        holder.binding.apply {
            tvFolderName.text = item.name
            cbChoose.onClick {
                getData().get(position).isSelected = cbChoose.isChecked
                changeStatus(position)
            }
            if(item.id == lastWordBookId) {
                lastIndex = position
                lastWordBookId = -2
            }
            cbChoose.isChecked = item.isSelected
        }
    }




    private fun changeStatus(position: Int) {
        val wordBook = getData().get(position)
        if (lastIndex == -1) {
            // 第1次选择操作
            wordBook.isSelected = true
            lastIndex = position
            notifyItemChanged(position)
        }
        else if(lastIndex == position) {
            // 反选操作,复原
            wordBook.isSelected = false
            lastIndex = -1
            notifyItemChanged(position)
        } else {
            // 选择其他条目操作，交换
            wordBook.isSelected = true
            getData().get(lastIndex).isSelected = false
            notifyItemChanged(position)
            notifyItemChanged(lastIndex)
            lastIndex = position
        }
    }


    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): ItemFolderBinding {
        return ItemFolderBinding.inflate(layoutInflater, parent, false)
    }
}