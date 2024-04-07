package com.gp.learn.game

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.gp.common.model.Option
import com.gp.framework.utils.getColorFromResource
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
        holder.textWordMean.text = itemWordMeanChoice.wordVi
        holder.imgChoice.visibility = View.GONE
        holder.textWordMean.setTextColor(getColorFromResource(com.gp.lib_widget.R.color.vd_dark_theme_color))
//        if (ConfigData.getIsNight()) holder.textWordMean.setTextColor(
//            MyApplication.getContext().getResources().getColor(R.color.colorLightBlack)
//        ) else holder.textWordMean.setTextColor(
//            MyApplication.getContext().getResources().getColor(R.color.colorLightBlack)
//        )
        if (!itemWordMeanChoice.isRight) {
            // 说明答错了
//            if (ConfigData.getIsNight()) holder.cardMean.setCardBackgroundColor(
//                MyApplication.getContext().getColor(R.color.colorLittleRedN)
//            ) else holder.cardMean.setCardBackgroundColor(
//                MyApplication.getContext().getColor(R.color.colorLittleRed)
//            )
//            holder.imgChoice.visibility = View.VISIBLE
//            Glide.with(MyApplication.getContext()).load(R.drawable.icon_wrong)
//                .into(holder.imgChoice)
//            if (ConfigData.getIsNight()) holder.textWordMean.setTextColor(
//                MyApplication.getContext().getColor(R.color.colorLightRedN)
//            ) else holder.textWordMean.setTextColor(
//                MyApplication.getContext().getColor(R.color.colorLightRed)
//            )
//        } else if (itemWordMeanChoice.isRight) {
//            // 说明答对了
//            if (ConfigData.getIsNight()) holder.cardMean.setCardBackgroundColor(
//                MyApplication.getContext().getResources().getColor(R.color.colorLittleBlueN)
//            ) else holder.cardMean.setCardBackgroundColor(
//                MyApplication.getContext().getResources().getColor(R.color.colorLittleBlue)
//            )
//            holder.imgChoice.visibility = View.VISIBLE
//            Glide.with(MyApplication.getContext()).load(R.drawable.icon_select_blue)
//                .into(holder.imgChoice)
//            if (ConfigData.getIsNight()) holder.textWordMean.setTextColor(
//                MyApplication.getContext().getResources().getColor(R.color.colorLightBlueN)
//            ) else holder.textWordMean.setTextColor(
//                MyApplication.getContext().getResources().getColor(R.color.colorLightBlue)
//            )
//        } else if (itemWordMeanChoice.isRight() === ItemWordMeanChoice.NOTSTART) {
//            if (ConfigData.getIsNight()) holder.cardMean.setCardBackgroundColor(
//                MyApplication.getContext().getColor(R.color.colorBgWhiteN)
//            ) else holder.cardMean.setCardBackgroundColor(
//                MyApplication.getContext().getColor(R.color.colorBgWhite)
//            )
//            holder.imgChoice.visibility = View.GONE
//            if (ConfigData.getIsNight()) holder.textWordMean.setTextColor(
//                MyApplication.getContext().getResources().getColor(R.color.colorLightBlackN)
//            ) else holder.textWordMean.setTextColor(
//                MyApplication.getContext().getResources().getColor(R.color.colorLightBlack)
//            )
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
