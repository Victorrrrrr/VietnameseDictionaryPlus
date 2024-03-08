package com.gp.search

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.gp.common.constant.SEARCH_ACTIVITY_SEARCH
import com.gp.framework.base.BaseMvvmActivity
import com.gp.framework.ext.onClick
import com.gp.framework.utils.LogUtil
import com.gp.framework.utils.SoftInputUtil
import com.gp.lib_framework.utils.StatusBarSettingHelper
import com.gp.mod_search.databinding.ActivitySearchBinding
import java.util.Timer
import java.util.TimerTask


@Route(path = SEARCH_ACTIVITY_SEARCH)
class SearchActivity : BaseMvvmActivity<ActivitySearchBinding, SearchViewModel>() {
    override fun initView(savedInstanceState: Bundle?) {
        StatusBarSettingHelper.setStatusBarTranslucent(this)
        StatusBarSettingHelper.statusBarLightMode(this, true)

        showEditTextInput()
        initEvent()

    }

    private fun initEvent() {
        // 搜索框键盘的搜索键点击监听
        mBinding.etSearchTextEdit.setOnEditorActionListener { view, actionId, event ->
            // TODO : 执行搜索操作
            LogUtil.d(tag = "search", message = "搜索操作执行")
            true
        }

        // 取消按钮点击监听
        mBinding.btnCancel.onClick {
            finish()
        }
    }


    private fun showEditTextInput() {
        mBinding.etSearchTextEdit.isFocusable = true
        mBinding.etSearchTextEdit.isFocusableInTouchMode = true
        mBinding.etSearchTextEdit.requestFocus()

        Timer().schedule(object : TimerTask() {
            override fun run() {
                SoftInputUtil.showSoftInput(mBinding.etSearchTextEdit)
            }
        },100)
    }


    override fun finish() {
        super.finish()
        // 取消退出动画
        overridePendingTransition(0,0)
    }

}