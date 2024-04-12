package com.gp.user.ui.collection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.gp.common.constant.USER_ACTIVITY_COLLECTION
import com.gp.framework.base.BaseMvvmActivity
import com.gp.framework.ext.onClick
import com.gp.framework.utils.getColorFromResource
import com.gp.lib_framework.utils.StatusBarSettingHelper
import com.gp.mod_user.R
import com.gp.mod_user.databinding.ActivityCollectionBinding
import com.gp.network.manager.WordBookIdManager

@Route(path = USER_ACTIVITY_COLLECTION)
class CollectionActivity : BaseMvvmActivity<ActivityCollectionBinding, CollectionViewModel>() {

    private var wordListAdapter : CollectionAdapter? = null

    override fun initView(savedInstanceState: Bundle?) {
        // 设置状态栏背景色
        getWindow().setStatusBarColor(getColorFromResource(com.gp.lib_widget.R.color.vd_theme_color));
        // 设置状态栏字体白色
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE)

        val folderId = WordBookIdManager.getFavFolderId()
        val linearLayoutManager = LinearLayoutManager(this)
        mBinding.recyclerFav.layoutManager = linearLayoutManager
        wordListAdapter = CollectionAdapter()
        mBinding.recyclerFav.adapter = wordListAdapter
        mBinding.toolbar.setNavigationOnClickListener { onBackPressed() }
        refreshList(folderId)
        wordListAdapter!!.setOnItemDelete(object : OnItemDelete {
            override fun onDelete(wordId: Int) {
                mViewModel.deleteWordInFolder(folderId, wordId) {
                    refreshList(folderId)
                }.observe(this@CollectionActivity) {}
            }

        })
    }


    fun refreshList(folderId : Int) {
        if(folderId == -1) return
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