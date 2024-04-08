package com.gp.learn.game

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.gp.common.model.ChoiceResult
import com.gp.common.model.Option
import com.gp.framework.helper.VDHelper
import com.gp.framework.utils.getColorFromResource
import com.gp.framework.utils.getDrawable
import com.gp.mod_learn.R


class MeanChoiceAdapter(mItemWordMeanChoiceList: List<Option>) :
    RecyclerView.Adapter<MeanChoiceAdapter.ViewHolder>(), View.OnClickListener {
    private var recyclerView: RecyclerView? = null
    private val mItemWordMeanChoiceList: List<Option>

    // 声明单击接口
    private var onItemClickListener: OnItemClickListener? = null
    fun setOnItemClickListener(onItemClickListener: OnItemClickListener?) {
        this.onItemClickListener = onItemClickListener
    }

    // 将RecyclerView附加到Adapter上
    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        this.recyclerView = null
    }

    override fun onClick(v: View) {
        val position = recyclerView!!.getChildAdapterPosition(v)
        if (onItemClickListener != null) {
            onItemClickListener!!.onItemClick(
                recyclerView, v, position,
                mItemWordMeanChoiceList[position]
            )
        }
    }

    class ViewHolder(meanView: View) : RecyclerView.ViewHolder(meanView) {
        var cardMean: CardView
        var imgChoice: ImageView
        var textWordMean: TextView

        init {
            cardMean = itemView.findViewById(R.id.item_card_word_choice)
            imgChoice = itemView.findViewById(R.id.item_img_word_choice_status)
            textWordMean = itemView.findViewById(R.id.item_text_word_means)
        }
    }

    init {
        this.mItemWordMeanChoiceList = mItemWordMeanChoiceList
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_word_mean_choice, parent, false)
        view.setOnClickListener(this)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemWordMeanChoice: Option = mItemWordMeanChoiceList[position]
        holder.textWordMean.text = itemWordMeanChoice.wordZh
        holder.imgChoice.visibility = View.GONE
        holder.textWordMean.setTextColor(getColorFromResource(com.gp.lib_widget.R.color.vd_dark_theme_color))
        holder.textWordMean.setTextColor(getColorFromResource(com.gp.lib_widget.R.color.color_light_black))
        if (itemWordMeanChoice.result == ChoiceResult.WRONG) {
            // 说明答错了
            holder.cardMean.setCardBackgroundColor(getColorFromResource(com.gp.lib_widget.R.color.wrong_chacha_item))
            holder.imgChoice.visibility = View.VISIBLE
            holder.imgChoice.setImageDrawable(getDrawable(com.gp.lib_widget.R.drawable.ic_chacha_red))
            holder.textWordMean.setTextColor(getColorFromResource(com.gp.lib_widget.R.color.color_light_red))
        } else if (itemWordMeanChoice.result == ChoiceResult.RIGHT) {
            // 说明答对了
            holder.cardMean.setCardBackgroundColor(getColorFromResource(com.gp.lib_widget.R.color.right_tick_item))
            holder.imgChoice.visibility = View.VISIBLE
            holder.imgChoice.setImageDrawable(getDrawable(com.gp.lib_widget.R.drawable.ic_tick_green))
            holder.textWordMean.setTextColor(getColorFromResource(com.gp.lib_widget.R.color.color_tick_green))
        } else if (itemWordMeanChoice.result == ChoiceResult.NOTSTART) {
            holder.cardMean.setCardBackgroundColor(getColorFromResource(com.gp.lib_widget.R.color.choice_item_white_bg))
            holder.imgChoice.visibility = View.GONE
            holder.textWordMean.setTextColor(getColorFromResource(com.gp.lib_widget.R.color.choice_item_text_color))
        }
    }

    override fun getItemCount(): Int {
        return mItemWordMeanChoiceList.size
    }

    companion object {
        // 判断是否是第一次点击
        var isFirstClick = true
        private const val TAG = "MeanChoiceAdapter"
    }
}


interface OnItemClickListener {
    fun onItemClick(
        parent: RecyclerView?,
        view: View?,
        position: Int,
        itemWordMeanChoice: Option?
    )
}
