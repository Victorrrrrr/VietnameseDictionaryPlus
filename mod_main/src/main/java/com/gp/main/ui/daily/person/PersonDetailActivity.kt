package com.gp.main.ui.daily.person

import android.os.Bundle
import com.gp.common.provider.MainServiceProvider
import com.gp.framework.base.BaseMvvmActivity
import com.gp.framework.ext.onClick
import com.gp.glide.setUrl
import com.gp.main.databinding.ActivityPersonDetailBinding
import com.gp.main.ui.daily.viewmodel.DailyViewModel

class PersonDetailActivity : BaseMvvmActivity<ActivityPersonDetailBinding, DailyViewModel>() {
    override fun initView(savedInstanceState: Bundle?) {
        mBinding.toolbarDailyDetail.setNavigationOnClickListener { onBackPressed() }
        mBinding.btnHome.onClick {
            MainServiceProvider.toMain(this, 0)
        }

    }

    override fun initData() {
        val pic = intent.getStringExtra("pic")
        val field = intent.getStringExtra("field")
        val nameVi = intent.getStringExtra("nameVi")
        val nameZh = intent.getStringExtra("nameZh")
        val descVi = intent.getStringExtra("descVi")
        val descZh = intent.getStringExtra("descZh")

        mBinding.ivPic.setUrl(pic)
        mBinding.tvDescV.append(descVi)
        mBinding.tvDescCn.append(descZh)
        mBinding.tvNameCn.append(nameZh)
        mBinding.tvNameV.text = nameVi
        mBinding.tvDomainCn.append(field)

        mBinding.tvNameTitle.text = "$nameVi - 人物详情"
    }
}