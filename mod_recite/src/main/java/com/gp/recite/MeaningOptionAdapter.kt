package com.gp.recite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gp.common.model.LearnWordBean
import com.gp.common.model.Option
import com.gp.framework.adapter.BaseBindViewHolder
import com.gp.framework.adapter.BaseRecyclerViewAdapter
import com.gp.framework.ext.onClick
import com.gp.framework.utils.getDrawable
import com.gp.mod_recite.R
import com.gp.mod_recite.databinding.ItemMeaningOptionBinding
import com.gp.recite.controller.WordController

class MeaningOptionAdapter : BaseRecyclerViewAdapter<Option, ItemMeaningOptionBinding>() {

    private var onOptionListener : OnOptionListener? = null

    fun setOnOptionListener(onOptionListener: OnOptionListener) {
        this.onOptionListener = onOptionListener
    }

    override fun onBindDefViewHolder(
        holder: BaseBindViewHolder<ItemMeaningOptionBinding>,
        item: Option?,
        position: Int
    ) {
        if(item == null) return
        holder.binding.apply {
//            llMeaningBg.background = getDrawable(com.gp.lib_widget.R.drawable.shape_bg_white_radius_12)
//            ivChooseResult.visibility = View.GONE
            if(WordController.isZhToVie) {
                pos = ""
                meaning = item.wordVi
            } else {
                pos = item.pos
                meaning = item.wordZh
            }

            tvOptionMeaning.onClick {
                if (item.isRight) {
                    onOptionListener?.onRight()
                    llMeaningBg.background = getDrawable(com.gp.lib_widget.R.drawable.shape_bg_tick_radius_12)
                    ivChooseResult.visibility = View.VISIBLE
                    ivChooseResult.setImageDrawable(getDrawable(com.gp.lib_widget.R.drawable.ic_tick_green))
                } else {
                    onOptionListener?.onFalse()
                    llMeaningBg.background = getDrawable(com.gp.lib_widget.R.drawable.shape_bg_wrong_radius_12)
                    ivChooseResult.visibility = View.VISIBLE
                    ivChooseResult.setImageDrawable(getDrawable(com.gp.lib_widget.R.drawable.ic_chacha_red))
                }
            }
        }
    }

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): ItemMeaningOptionBinding {
        return ItemMeaningOptionBinding.inflate(layoutInflater, parent, false)
    }
}


interface OnOptionListener {
    fun onRight()
    fun onFalse()
}