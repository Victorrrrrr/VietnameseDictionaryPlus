package com.gp.learn.tool.note

import android.os.Bundle
import android.widget.Toast
import com.gp.common.model.FolderAddRequest
import com.gp.framework.base.BaseMvvmActivity
import com.gp.framework.ext.onClick
import com.gp.framework.toast.TipsToast
import com.gp.mod_learn.databinding.ActivityAddFolderBinding


class AddFolderActivity : BaseMvvmActivity<ActivityAddFolderBinding, FolderViewModel>() {

    override fun initView(savedInstanceState: Bundle?) {
        mBinding.ivBack.onClick {
            onBackPressed()
        }

        mBinding.layoutAfAdd.onClick {
            val name = mBinding.editAfName.text.toString().trim()
            if (name.isNotEmpty()) {
                val desc = mBinding.editAfRemark.text.toString().trim()
                val wordFolder = if (desc.isNotEmpty())
                    FolderAddRequest(name = name ,desc = desc)
                else
                    FolderAddRequest(name = name, desc = "")
                mViewModel.addFolder(wordFolder){
                    onBackPressed()
                    TipsToast.showTips("新建成功")
                }.observe(this) {

                }

            } else {
                TipsToast.showTips( "请输入完整")
            }
        }
    }
}