package com.gp.main.ui.daily.adapter


import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import com.gp.common.model.PersonDaily
import com.gp.framework.adapter.BaseBindViewHolder
import com.gp.framework.adapter.BaseRecyclerViewAdapter
import com.gp.framework.ext.onClick
import com.gp.glide.setUrl
import com.gp.main.databinding.ItemDailyBinding
import com.gp.main.ui.daily.person.PersonDetailActivity

class DailyPersonAdapter : BaseRecyclerViewAdapter<PersonDaily, ItemDailyBinding>(){
    override fun onBindDefViewHolder(
        holder: BaseBindViewHolder<ItemDailyBinding>,
        item: PersonDaily?,
        position: Int
    ) {
        if (item == null) return
        holder.binding.apply {
            ivPic.setUrl(item.pic)
            tvDomainCn.text = "领域：${item.field}"
            tvNameCn.text = "中文名：${item.nameZh}"
            tvNameV.text = item.nameVi

            tvMoreBtn.onClick {
                val intent = Intent(it.context, PersonDetailActivity::class.java)
                intent.putExtra("pic", item.pic)
                intent.putExtra("field", item.field)
                intent.putExtra("nameVi", item.nameVi)
                intent.putExtra("nameZh", item.nameZh)
                intent.putExtra("descVi", item.descVi)
                intent.putExtra("descZh", item.descZh)
                it.context.startActivity(intent)
            }
        }
    }

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): ItemDailyBinding {
        return ItemDailyBinding.inflate(layoutInflater, parent, false)
    }
}