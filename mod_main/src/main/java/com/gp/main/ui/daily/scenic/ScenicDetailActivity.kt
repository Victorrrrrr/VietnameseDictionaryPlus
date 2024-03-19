package com.gp.main.ui.daily.scenic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gp.common.provider.MainServiceProvider
import com.gp.framework.base.BaseMvvmActivity
import com.gp.framework.ext.onClick
import com.gp.glide.setUrl
import com.gp.main.R
import com.gp.main.databinding.ActivityScenicDetailBinding
import com.gp.main.ui.daily.viewmodel.DailyViewModel

class ScenicDetailActivity : BaseMvvmActivity<ActivityScenicDetailBinding, DailyViewModel>() {
    override fun initView(savedInstanceState: Bundle?) {
        mBinding.toolbarDailyDetail.setNavigationOnClickListener { onBackPressed() }
        mBinding.btnHome.onClick {
            MainServiceProvider.toMain(this, 0)
        }
    }

    override fun initData() {
        val pic = intent.getStringExtra("img")
        val field = intent.getStringExtra("location")
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

        mBinding.tvNameTitle.text = "$nameVi - 景点详情"
    }

}