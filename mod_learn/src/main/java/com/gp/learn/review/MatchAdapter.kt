package com.gp.learn.review


import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.gp.framework.helper.VDHelper
import com.gp.framework.toast.TipsToast
import com.gp.framework.utils.MediaHelper.playLocalFile
import com.gp.framework.utils.getColorFromResource
import com.gp.learn.data.IdAnalyse
import com.gp.learn.data.ItemMatch
import com.gp.learn.show.ShowActivity
import com.gp.mod_learn.R


class MatchAdapter(mItemMatchList: MutableList<ItemMatch>) :
    RecyclerView.Adapter<MatchAdapter.ViewHolder>() {
    private val idAnalyseList: MutableList<IdAnalyse> = ArrayList()
    private val mItemMatchList: MutableList<ItemMatch>
    private val showActivity = ShowActivity()

    class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        var cardView: CardView
        var textView: TextView

        init {
            cardView = itemView.findViewById(R.id.card_mt)
            textView = itemView.findViewById(R.id.text_mt_word)
        }
    }

    init {
        this.mItemMatchList = mItemMatchList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_match, parent, false)
        val holder = ViewHolder(view)
        holder.view.setOnClickListener {
            val position = holder.adapterPosition
            val itemMatch: ItemMatch = mItemMatchList[position]
            // 一开始没有
            if (idAnalyseList.isEmpty()) {
                idAnalyseList.add(IdAnalyse(itemMatch.id, position))
                itemMatch.isChosen = true
                notifyDataSetChanged()
            } else if (idAnalyseList.size == 1) {
                if (idAnalyseList[0].wordId == itemMatch.id && idAnalyseList[0].position != position) {
                    idAnalyseList.add(IdAnalyse(itemMatch.id, position))
                    val itemMatches: MutableList<ItemMatch> = ArrayList()
                    itemMatches.add(mItemMatchList[idAnalyseList[0].position])
                    itemMatches.add(mItemMatchList[position])
                    mItemMatchList.removeAll(itemMatches)
                    notifyItemRemoved(position)
                    notifyItemChanged(position, mItemMatchList.size)
                    if (idAnalyseList[0].position == 0) {
                        notifyItemRemoved(0)
                        notifyItemChanged(0, mItemMatchList.size)
                    } else {
                        notifyItemRemoved(idAnalyseList[0].position - 1)
                        notifyItemChanged(idAnalyseList[0].position - 1, mItemMatchList.size)
                    }
                    notifyItemChanged(0, mItemMatchList.size)
                    idAnalyseList.clear()
                    playLocalFile(R.raw.right)
                    if (mItemMatchList.isEmpty()) {
                        Toast.makeText(VDHelper.getApplication(), "匹配完成", Toast.LENGTH_SHORT)
                            .show()
                        val intent = Intent()
                        intent.setClass(VDHelper.getApplication(), ShowActivity::class.java)
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        intent.putExtra(
                            ShowActivity.SHOW_TYPE,
                            ShowActivity.TYPE_MATCH
                        )
                        VDHelper.getApplication().startActivity(intent)
                    }
                } else {
                    val itemMatch1: ItemMatch = mItemMatchList[idAnalyseList[0].position]
                    itemMatch1.isChosen = false
                    val itemMatch2: ItemMatch = mItemMatchList[position]
                    itemMatch2.isChosen = false
                    notifyDataSetChanged()
                    idAnalyseList.clear()
                    playLocalFile(R.raw.wrong)
                    TipsToast.showTips("点错了哦")
                }
            }
        }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemMatch: ItemMatch = mItemMatchList[position]
        holder.textView.text = itemMatch.wordString
        if (itemMatch.isChosen) {
            holder.cardView.setCardBackgroundColor(
                getColorFromResource(com.gp.lib_widget.R.color.vd_theme_color)
            )
            holder.textView.setTextColor(
                getColorFromResource(com.gp.lib_widget.R.color.light_white)
            )

        } else {
            holder.cardView.setCardBackgroundColor(
                getColorFromResource(com.gp.lib_widget.R.color.white_bg)
            )
            holder.textView.setTextColor(
                getColorFromResource(com.gp.lib_widget.R.color.black_80000000)
            )
        }
    }

    override fun getItemCount(): Int {
        return mItemMatchList.size
    }
}