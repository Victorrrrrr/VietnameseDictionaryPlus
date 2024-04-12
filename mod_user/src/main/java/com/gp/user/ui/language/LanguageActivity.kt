package com.gp.user.ui.language

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import com.alibaba.android.arouter.facade.annotation.Route
import com.gp.common.constant.USER_ACTIVITY_LANGUAGE
import com.gp.common.provider.MainServiceProvider
import com.gp.framework.base.BaseDataBindActivity
import com.gp.framework.ext.dp2px
import com.gp.framework.ext.onClick
import com.gp.framework.utils.LanguageChangeUtils
import com.gp.framework.utils.MMKVUtil
import com.gp.framework.utils.MMKV_TYPE
import com.gp.lib_widget.databinding.DialogTipsRestartAppBinding
import com.gp.mod_user.databinding.ActivityLanguageBinding
import com.gp.widget.AlertDialog

@Route(path = USER_ACTIVITY_LANGUAGE)
class LanguageActivity : BaseDataBindActivity<ActivityLanguageBinding>() {

    private var numDialog : AlertDialog? = null
    private val lastLanguage = MMKVUtil.get(MMKV_TYPE.APP).decodeString("LANGUAGE")

    override fun initView(savedInstanceState: Bundle?) {

        //init ckb
        if (MMKVUtil.get(MMKV_TYPE.APP).decodeString("LANGUAGE").equals("ENGLISH")) {
            mBinding.cbChooseEnglish.isChecked = true
        }else{
            mBinding.cbChooseChinese.isChecked = true
        }

        //back
        mBinding.toolbarLanguage.setNavigationOnClickListener{
            onBackPressed()
        }

        //confirm click
        mBinding.tvLanguageChooseConfirm.onClick {
            if (mBinding.cbChooseEnglish.isChecked){
                if (!lastLanguage.equals("ENGLISH")){
                    MMKVUtil.get(MMKV_TYPE.APP).encode("LANGUAGE","ENGLISH")
                    showRestartAppTipDialog();//toast
                }
            }else{
                if (!lastLanguage.equals("CHINESE")){
                    MMKVUtil.get(MMKV_TYPE.APP).encode("LANGUAGE","CHINESE")
                    showRestartAppTipDialog();//toast
                }
            }
        }

        //ckb click
        mBinding.viewChineseCheck.onClick {
            if (!mBinding.cbChooseChinese.isChecked){
                mBinding.cbChooseChinese.isChecked = true
                mBinding.cbChooseEnglish.isChecked = false
            }
        }

        mBinding.viewEnglishCheck.onClick {
            if (!mBinding.cbChooseEnglish.isChecked){
                mBinding.cbChooseChinese.isChecked = false
                mBinding.cbChooseEnglish.isChecked = true
            }
        }

    }

    //弹窗提示直接根据选中重启
    private fun showRestartAppTipDialog(){
        val dialogEditBinding: DialogTipsRestartAppBinding = DataBindingUtil.inflate(
            LayoutInflater.from(this),
            com.gp.lib_widget.R.layout.dialog_tips_restart_app,
            null,
            false
        )

        val builder = AlertDialog.Builder(this)
            .addDefaultAnimation()
            .setCancelable(false)
            .setContentView(dialogEditBinding.root)
            .setWidthAndHeight(dp2px( 300f), LinearLayout.LayoutParams.WRAP_CONTENT)
            .setText(com.gp.lib_widget.R.id.tv_title, resources.getString(com.gp.lib_widget.R.string.toast_restart))
            .setText(com.gp.lib_widget.R.id.tv_content,resources.getString(com.gp.lib_widget.R.string.restarting_the_application_takes_effect))
            .setOnClickListener(com.gp.lib_widget.R.id.tv_sure) {
                LanguageChangeUtils.restartApplication(this)
                finish()
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