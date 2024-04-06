package com.gp.search.ui.word

import android.view.LayoutInflater
import android.view.ViewGroup
import com.gp.common.model.SentenceBean
import com.gp.framework.adapter.BaseBindViewHolder
import com.gp.framework.adapter.BaseRecyclerViewAdapter
import com.gp.framework.ext.onClick
import com.gp.mod_search.databinding.LayoutSentenceItemBinding

class SentencesAdapter : BaseRecyclerViewAdapter<SentenceBean, LayoutSentenceItemBinding>() {
    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): LayoutSentenceItemBinding {
        return LayoutSentenceItemBinding.inflate(layoutInflater, parent, false)
    }


    override fun onBindDefViewHolder(
        holder: BaseBindViewHolder<LayoutSentenceItemBinding>,
        item: SentenceBean?,
        position: Int
    ) {
        if (item == null) return

        holder.binding.apply {
            tvItemSentenceText.text = item.sentenceV
            tvItemSentenceExplain.text = item.sentenceC

            ivItemSentenceSound.onClick {
                // TODO：播放音频

            }

            ivItemSentenceFav.onClick {

            }


            ivItemSentenceSuggest.onClick {

            }

        }


//        val name = if (item.author.isNullOrEmpty()) item.shareUser else item.author
//        val authorName = String.format(getStringFromResource(R.string.author_name), name)
//        holder.binding.apply {
//            tvTitle.text = item.title
//            tvTitle.Bold()
//            tvDesc.text = item.desc
//            if (item.desc.isNullOrEmpty()) {
//                tvDesc.gone()
//            } else {
//                tvDesc.visible()
//            }
//            tvTime.text = format.format(item.publishTime)
//            tvFrom.text = "${item.superChapterName}/${item.chapterName}"
//            tvAuthorName.text = authorName
//            tvZan.text = "${item.zan ?: "0"}"
//            ivCollect.onClick {
//                onItemCollectListener?.invoke(it, position)
//            }
//            ivCollect.isSelected = item.collect ?: false
//        }


    }


}