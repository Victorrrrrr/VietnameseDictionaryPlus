package com.gp.user.ui.collection

import android.view.LayoutInflater
import android.view.ViewGroup
import com.gp.common.model.WordBeanItem
import com.gp.common.provider.SearchServiceProvider
import com.gp.framework.adapter.BaseBindViewHolder
import com.gp.framework.adapter.BaseRecyclerViewAdapter
import com.gp.framework.ext.onClick
import com.gp.framework.utils.LogUtil
import com.gp.mod_user.databinding.ItemFabBinding

class CollectionAdapter : BaseRecyclerViewAdapter<WordBeanItem,ItemFabBinding>() {

    private var onItemDelete: OnItemDelete? = null

    fun setOnItemDelete(onItemDelete: OnItemDelete) {
        this.onItemDelete = onItemDelete
    }

    override fun onBindDefViewHolder(
        holder: BaseBindViewHolder<ItemFabBinding>,
        item: WordBeanItem?,
        position: Int
    ) {
        holder.binding.apply {
            textItwfdWord.text = item?.wordVi
            textItwfdMean.text = "${item?.pos}.  ${item?.wordZh}"
            textItwfdWord.onClick {
                SearchServiceProvider.toWordPage(it.context, item?.id.toString())
            }

            imgFavReduce.onClick {
                LogUtil.d(tag = "111", message = "click delete")
                onItemDelete!!.onDelete(item!!.id)
            }
        }
    }

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): ItemFabBinding {
        return ItemFabBinding.inflate(layoutInflater, parent, false)
    }
}

interface OnItemDelete {
    fun onDelete(wordId : Int)
}