package com.gp.search.ui.adapter


import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import com.gp.common.model.SingleWordBean
import com.gp.framework.adapter.BaseBindViewHolder
import com.gp.framework.adapter.BaseRecyclerViewAdapter
import com.gp.framework.adapter.BaseViewHolder
import com.gp.framework.ext.click
import com.gp.framework.ext.onClick
import com.gp.mod_search.databinding.ItemWordSearchBinding
import com.gp.network.constant.KEY_WORD_ID
import com.gp.search.ui.word.WordPageActivity

class WordSearchAdapter : BaseRecyclerViewAdapter<SingleWordBean, ItemWordSearchBinding>() {
    override fun onBindDefViewHolder(
        holder: BaseBindViewHolder<ItemWordSearchBinding>,
        item: SingleWordBean?,
        position: Int
    ) {
        if (item == null) return
        holder.binding.apply {
            tvWordSearch.text = item.wordVi
            tvWordPos.text = item.pos
            tvWordExplainSearch.text = item.wordZh
        }

        holder.itemView.onClick{
            val intent = Intent(it.context, WordPageActivity::class.java)
            intent.putExtra(KEY_WORD_ID, item.id.toString())
            it.context.startActivity(intent)
        }
    }

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): ItemWordSearchBinding {
        return ItemWordSearchBinding.inflate(layoutInflater, parent, false)
    }
}