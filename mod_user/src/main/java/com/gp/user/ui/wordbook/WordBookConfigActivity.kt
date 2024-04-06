package com.gp.user.ui.wordbook

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.gp.common.constant.USER_ACTIVITY_WORDBOOK
import com.gp.common.provider.MainServiceProvider
import com.gp.framework.base.BaseMvvmActivity
import com.gp.framework.ext.dp2px
import com.gp.framework.ext.onClick
import com.gp.framework.toast.TipsToast
import com.gp.framework.utils.getColorFromResource
import com.gp.lib_widget.databinding.DialogEditBinding
import com.gp.lib_widget.databinding.DialogModifyBinding
import com.gp.mod_user.databinding.ActivityWordBookConfigBinding
import com.gp.network.manager.WordBookIdManager
import com.gp.widget.AlertDialog

@Route(path = USER_ACTIVITY_WORDBOOK)
class WordBookConfigActivity : BaseMvvmActivity<ActivityWordBookConfigBinding, WordBookViewModel>() {

    private var numDialog : AlertDialog? = null
    private var adapter : WordbookAdapter? = null
    override fun initView(savedInstanceState: Bundle?) {
        // 设置状态栏背景色
        getWindow().setStatusBarColor(getColorFromResource(com.gp.lib_widget.R.color.vd_theme_color));
        // 设置状态栏字体白色
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE)

        mBinding.toolbarWordbook.setNavigationOnClickListener {
            onBackPressed()
        }

        val lastTimeId = WordBookIdManager.getWordBookId()
        adapter =  WordbookAdapter(-1)
        mViewModel.getWordbookList(1, 20, "").observe(this) {
            mBinding.rvWordBook.adapter = adapter
            mBinding.rvWordBook.layoutManager = LinearLayoutManager(this)
            it?.data?.forEach { wordbook ->
                if(wordbook.id == lastTimeId) {
                    wordbook.isSelected = true
                }
            }
            adapter?.setData(it?.data)
        }

        mBinding.tvWordBookChooseConfirm.onClick {
            showEditNumDialog()
        }
    }

    private fun showEditNumDialog() {
        val dialogEditBinding: DialogEditBinding = DataBindingUtil.inflate(
            LayoutInflater.from(this),
            com.gp.lib_widget.R.layout.dialog_edit,
            null,
            false
        )

        dialogEditBinding.etContent.inputType = InputType.TYPE_CLASS_NUMBER
        val builder = AlertDialog.Builder(this)
            .addDefaultAnimation()
            .setCancelable(false)
            .setContentView(dialogEditBinding.root)
            .setWidthAndHeight(dp2px( 300f), LinearLayout.LayoutParams.WRAP_CONTENT)
            .setText(com.gp.lib_widget.R.id.tv_title, "每次单词数量设置")
            .setEditTextHint(com.gp.lib_widget.R.id.et_content, "请输入每次学习单词的数量，不超过词书总数")
            .setOnClickListener(com.gp.lib_widget.R.id.tv_sure) {
                val count = dialogEditBinding.etContent.text.toString().toInt()
                WordBookIdManager.saveReciteNum(count)
                // 设置词书配置
                val list = adapter?.getData()
                if (list != null) {
                    for (wordBook in list) {
                        if(wordBook.isSelected) {
                            mViewModel.postWordbookId(wordBook.id) {
                                WordBookIdManager.saveWordBookId(wordBook.id)
                                TipsToast.showTips("选择词书${wordBook.name}成功")
                                onBackPressed()
                            }.observe(this) {

                            }
                        }
                    }
                }
            }.setOnClickListener(com.gp.lib_widget.R.id.tv_cancel) {
                numDialog?.dismiss()
            }
        numDialog = builder.create()
        numDialog?.show()
    }


    override fun onBackPressed() {
        super.onBackPressed()
        MainServiceProvider.toMain(this, 3)
    }

}