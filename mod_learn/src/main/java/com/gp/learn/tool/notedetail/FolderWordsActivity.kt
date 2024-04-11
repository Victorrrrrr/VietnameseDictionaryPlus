package com.gp.learn.tool.notedetail

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.gp.common.model.LearnWordBean
import com.gp.framework.base.BaseMvvmActivity
import com.gp.framework.ext.onClick
import com.gp.learn.tool.note.FolderViewModel
import com.gp.mod_learn.databinding.ActivityNoteDetailBinding
import com.gp.network.constant.KEY_FOLDER_ID

class FolderWordsActivity : BaseMvvmActivity<ActivityNoteDetailBinding, FolderViewModel>() {

    private var wordListAdapter: WordFolderListAdapter? = null

    private val editType = arrayOf("更改名称", "更改备注")

    private var wordList : List<LearnWordBean>? = null

    override fun initView(savedInstanceState: Bundle?) {

        val folderId = intent.getIntExtra(KEY_FOLDER_ID, -1)
        val desc = intent.getStringExtra("desc")
        val name = intent.getStringExtra("name")
        val linearLayoutManager = LinearLayoutManager(this)
        mBinding.recyclerFd.layoutManager = linearLayoutManager
        wordListAdapter = WordFolderListAdapter()
        mBinding.recyclerFd.adapter = wordListAdapter
        mBinding.textFdRemark.text = desc
        mBinding.textFdName.text = name
        mBinding.imgFdStart.onClick { onBackPressed() }
        refreshList(folderId)
        wordListAdapter!!.setOnItemDelete(object : OnItemDelete {
            override fun onDelete(wordId: Int) {
                mViewModel.deleteWordInFolder(folderId, wordId) {
                    refreshList(folderId)
                }.observe(this@FolderWordsActivity) {}
            }

        })


        mBinding.imgFdStart.visibility = View.GONE

//        mBinding.imgFdStart.onClick {
//            ReciteServiceProvider.toReciteWord(it.context, )
//        }
    }





    fun refreshList(folderId : Int) {
        mViewModel.getFolderWordList(folderId, 1, 100, "").observe(this) {
            wordListAdapter!!.setData(it.data)
            wordListAdapter!!.notifyDataSetChanged()
            if(it.data.size == 0) {
                mBinding.ivNoResult.visibility = View.VISIBLE
                mBinding.tvNoResult.visibility = View.VISIBLE
            } else {
                mBinding.ivNoResult.visibility = View.GONE
                mBinding.tvNoResult.visibility = View.GONE
            }
        }
    }

}