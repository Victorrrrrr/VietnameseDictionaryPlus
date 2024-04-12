package com.gp.learn.show

import android.view.LayoutInflater
import android.view.ViewGroup
import com.gp.common.provider.SearchServiceProvider
import com.gp.framework.adapter.BaseBindViewHolder
import com.gp.framework.adapter.BaseRecyclerViewAdapter
import com.gp.framework.ext.onClick
import com.gp.learn.data.ItemShow
import com.gp.mod_learn.databinding.ItemShowBinding


class ShowWordAdapter :
    BaseRecyclerViewAdapter<ItemShow, ItemShowBinding>() {

    override fun onBindDefViewHolder(
        holder: BaseBindViewHolder<ItemShowBinding>,
        itemShow: ItemShow?,
        position: Int
    ) {
        holder.binding.apply {
            textIsMean.text = itemShow?.meaning
            textIsName.text = itemShow?.wordVi
            imgIsStar.setOnClickListener {
//            if (itemShow.isStar()) {
//                itemShow.setStar(false)
//                val word = Word()
//                word.setToDefault("isCollected")
//                word.updateAll("wordId = ?", itemShow.getWordId() + "")
//            } else {
//                itemShow.setStar(true)
//                val word = Word()
//                word.setIsCollected(1)
//                word.updateAll("wordId = ?", itemShow.getWordId() + "")
//            }
                notifyDataSetChanged()
            }

            layoutIs.onClick {
                SearchServiceProvider.toWordPage(it.context, itemShow?.id.toString())
            }
        }
    }

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): ItemShowBinding {
        return ItemShowBinding.inflate(layoutInflater, parent, false)
    }
}

