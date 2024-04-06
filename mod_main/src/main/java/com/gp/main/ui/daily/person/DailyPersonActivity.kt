package com.gp.main.ui.daily.person

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.gp.common.model.PersonDaily
import com.gp.framework.base.BaseMvvmActivity
import com.gp.framework.ext.onClick
import com.gp.framework.toast.TipsToast
import com.gp.framework.utils.LogUtil
import com.gp.glide.setUrl
import com.gp.lib_framework.utils.StatusBarSettingHelper
import com.gp.main.databinding.ActivityDailyPersonBinding
import com.gp.main.ui.daily.adapter.DailyPersonAdapter
import com.gp.main.ui.daily.viewmodel.DailyViewModel

class DailyPersonActivity :BaseMvvmActivity<ActivityDailyPersonBinding, DailyViewModel>(){

    private lateinit var dailyPersonAdapter: DailyPersonAdapter
    override fun initView(savedInstanceState: Bundle?) {
        StatusBarSettingHelper.setStatusBarTranslucent(this)

        val manager = LinearLayoutManager(this)
        dailyPersonAdapter = DailyPersonAdapter()
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(mBinding.rvPersonList)
        mBinding.rvPersonList.apply {
            adapter = dailyPersonAdapter
            layoutManager = manager
        }

        mViewModel.getPersonDailyData().observe(this) {
            val list = mutableListOf<PersonDaily>()
            it.forEach {p ->
                list.add(p!!)
            }

            dailyPersonAdapter.setData(list)
        }


    }

}