package com.gp.learn.tool.note

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import com.gp.common.model.FolderListBean
import com.gp.framework.adapter.BaseBindViewHolder
import com.gp.framework.adapter.BaseRecyclerViewAdapter
import com.gp.framework.ext.dp2px
import com.gp.framework.ext.onClick
import com.gp.learn.tool.notedetail.FolderWordsActivity
import com.gp.lib_widget.databinding.DialogTipBinding
import com.gp.mod_learn.databinding.ItemNoteBinding
import com.gp.network.constant.KEY_FOLDER_ID
import com.gp.network.manager.WordBookIdManager
import com.gp.widget.AlertDialog


class FolderAdapter : BaseRecyclerViewAdapter<FolderListBean, ItemNoteBinding>(){

    private var deleteDialog : AlertDialog? = null
    private var onItemDelete : OnItemDelete? = null


    fun setOnItemDelete(onItemDelete: OnItemDelete) {
        this.onItemDelete = onItemDelete
    }

    override fun onBindDefViewHolder(
        holder: BaseBindViewHolder<ItemNoteBinding>,
        item: FolderListBean?,
        position: Int
    ) {
        holder.binding.apply {
            textItflName.text = item?.name
            textItflRemark.text = item?.desc
            textNoteWordNum.text = item?.count.toString()

            viewItemNote.onClick{
                // 跳转详情
                val intent = Intent(it.context, FolderWordsActivity::class.java)
                intent.putExtra(KEY_FOLDER_ID, item?.id)
                intent.putExtra("name", item?.name)
                intent.putExtra("desc", item?.desc)
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                it.context.startActivity(intent)
            }

            imgNoteDelete.onClick {
                // 删除文件夹
                showDeleteDialog(it.context, item?.id)
            }
        }
    }


    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): ItemNoteBinding {
        return ItemNoteBinding.inflate(layoutInflater, parent, false)
    }

    private fun showDeleteDialog(context: Context, folderId : Int?) {
        val dialogTipBinding: DialogTipBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            com.gp.lib_widget.R.layout.dialog_tip,
            null,
            false
        )

        val builder = AlertDialog.Builder(context)
            .addDefaultAnimation()
            .setCancelable(true)
            .setContentView(dialogTipBinding.root)
            .setWidthAndHeight(dp2px( 300f), LinearLayout.LayoutParams.WRAP_CONTENT)
            .setText(com.gp.lib_widget.R.id.tv_title, "是否确认删除该单词夹")
            .setOnClickListener(com.gp.lib_widget.R.id.tv_sure) {
                // 删除单词
                folderId?.let {
                    onItemDelete?.onDeleteClick(it)
                    deleteDialog?.dismiss()
                }
            }.setOnClickListener(com.gp.lib_widget.R.id.tv_cancel) {
                deleteDialog?.dismiss()
            }
        deleteDialog = builder.create()
        deleteDialog?.show()
    }
}

interface OnItemDelete {
    fun onDeleteClick(folder: Int)
}