package com.gp.learn.tool.note

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.gp.common.constant.LEARN_ACTIVITY_NOTE
import com.gp.framework.base.BaseMvvmActivity
import com.gp.framework.ext.onClick
import com.gp.framework.toast.TipsToast
import com.gp.learn.tool.notedetail.FolderWordsActivity
import com.gp.mod_learn.databinding.ActivityNoteBinding


@Route(path = LEARN_ACTIVITY_NOTE)
class FolderActivity : BaseMvvmActivity<ActivityNoteBinding, FolderViewModel>() {

    private var wordFolderAdapter: FolderAdapter? = null

    override fun initView(savedInstanceState: Bundle?) {
        windowExplode()
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        mBinding.recyclerWf.layoutManager = layoutManager
        wordFolderAdapter = FolderAdapter()
        mBinding.recyclerWf.adapter = wordFolderAdapter
        wordFolderAdapter!!.setOnItemDelete(object : OnItemDelete {
            override fun onDeleteClick(folder: Int) {
                mViewModel.deleteFolderById(folder) {
                    TipsToast.showTips("删除成功")
                    refresh()
                }.observe(this@FolderActivity){}
            }
        })

        mBinding.ivBack.onClick {
            onBackPressed()
        }

        mBinding.imgWfAdd.setOnClickListener {
            val intent = Intent(this@FolderActivity, AddFolderActivity::class.java)
            startActivity(intent)
        }
    }


    override fun onStart() {
        super.onStart()
        refresh()
    }


    private fun refresh() {
        mViewModel.getFolderList().observe(this) {
            wordFolderAdapter?.setData(it.data)
        }
        wordFolderAdapter?.notifyDataSetChanged()
    }
}
