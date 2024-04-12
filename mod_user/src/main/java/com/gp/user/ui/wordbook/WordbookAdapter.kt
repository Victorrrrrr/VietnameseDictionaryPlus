package com.gp.user.ui.wordbook

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.gp.common.model.WordBook
import com.gp.framework.adapter.BaseBindViewHolder
import com.gp.framework.adapter.BaseRecyclerViewAdapter
import com.gp.framework.adapter.BaseViewHolder
import com.gp.framework.ext.onClick
import com.gp.framework.utils.LogUtil
import com.gp.mod_user.databinding.ItemWorkBookBinding
import com.gp.network.manager.WordBookIdManager
import com.gp.room.entity.PersonInfo
import java.lang.String
import kotlin.Any
import kotlin.Int
import kotlin.apply


class WordbookAdapter(lastIndex : Int) : BaseRecyclerViewAdapter<WordBook, ItemWorkBookBinding>() {

    private var lastIndex = -1
    private var lastWordBookId = -1

    init {
        this.lastIndex = lastIndex
        lastWordBookId = WordBookIdManager.getWordBookId()
    }

    override fun onBindDefViewHolder(
        holder: BaseBindViewHolder<ItemWorkBookBinding>,
        item: WordBook?,
        position: Int
    ) {
        if (item == null) return
        holder.binding.apply {
            wordbookName = item.name
            wordbookCount = item.count
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

        for (wordBook in getData()) {
            LogUtil.d(tag = "wordbook", message = "title :  ${wordBook.name}  isSelected : ${wordBook.isSelected}")
        }

    }


    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): ItemWorkBookBinding {
        return ItemWorkBookBinding.inflate(layoutInflater, parent, false)
    }

}

