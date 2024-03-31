package com.gp.search.ui.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.gp.common.constant.SEARCH_ACTIVITY_SEARCH
import com.gp.framework.base.BaseMvvmActivity
import com.gp.framework.ext.onClick
import com.gp.framework.utils.LogUtil
import com.gp.framework.utils.SoftInputUtil
import com.gp.lib_framework.utils.StatusBarSettingHelper
import com.gp.mod_search.databinding.ActivitySearchBinding
import com.gp.search.ui.adapter.WordSearchAdapter
import java.util.Timer
import java.util.TimerTask


@Route(path = SEARCH_ACTIVITY_SEARCH)
class SearchActivity : BaseMvvmActivity<ActivitySearchBinding, SearchViewModel>() {

    var wordSearchAdapter : WordSearchAdapter? = null

    override fun initView(savedInstanceState: Bundle?) {
        StatusBarSettingHelper.setStatusBarTranslucent(this)
        StatusBarSettingHelper.statusBarLightMode(this, true)

        showEditTextInput()
        initEvent()
    }

    private fun initEvent() {
        wordSearchAdapter = WordSearchAdapter()

        mBinding.etSearchTextEdit.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                //如果改变了，并且文本长度>0
                val wordText = s.toString()
                if (wordText.length > 0) {
                    mViewModel.getWordList(1, 20, wordText).observe(this@SearchActivity) {
                        wordSearchAdapter?.setData(it.data)
                        mBinding.rvSearchWordList.layoutManager = LinearLayoutManager(this@SearchActivity)
                        mBinding.rvSearchWordList.adapter = wordSearchAdapter
                        mBinding.ivNoResult.visibility = if (it.data.size == 0) View.VISIBLE else View.GONE
                        mBinding.tvNoResult.visibility = if (it.data.size == 0) View.VISIBLE else View.GONE
                        wordSearchAdapter?.notifyDataSetChanged()
                    }

                } else {
                    //如果改变了，并且文本长度=0
                    mBinding.ivNoResult.visibility = View.VISIBLE
                    mBinding.tvNoResult.visibility = View.VISIBLE
                }
            }

        })

        // 搜索框键盘的搜索键点击监听
        mBinding.etSearchTextEdit.setOnEditorActionListener { view, actionId, event ->
            // TODO : 执行搜索操作
            LogUtil.d(tag = "search", message = "搜索操作执行")
            val wordText = view.text.toString()
            mViewModel.getWordList(1, 20, wordText).observe(this) {
                wordSearchAdapter?.setData(it.data)
                mBinding.rvSearchWordList.layoutManager = LinearLayoutManager(this)
                mBinding.rvSearchWordList.adapter = wordSearchAdapter
                mBinding.ivNoResult.visibility = if (it.data.size == 0) View.VISIBLE else View.GONE
                mBinding.tvNoResult.visibility = if (it.data.size == 0) View.VISIBLE else View.GONE
                wordSearchAdapter?.notifyDataSetChanged()
            }

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