package com.gp.main.ui.daily.scenic

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.gp.common.model.SceneryDaily
import com.gp.framework.base.BaseMvvmActivity
import com.gp.lib_framework.utils.StatusBarSettingHelper
import com.gp.main.databinding.ActivityDailyScenicBinding
import com.gp.main.ui.daily.adapter.DailySceneryAdapter
import com.gp.main.ui.daily.viewmodel.DailyViewModel

class DailyScenicActivity : BaseMvvmActivity<ActivityDailyScenicBinding, DailyViewModel>() {

    private lateinit var dailyScenicAdapter : DailySceneryAdapter
    override fun initView(savedInstanceState: Bundle?) {
        StatusBarSettingHelper.setStatusBarTranslucent(this)

        val manager = LinearLayoutManager(this)
        dailyScenicAdapter = DailySceneryAdapter()
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(mBinding.rvSceneryList)
        mBinding.rvSceneryList.apply {
            adapter = dailyScenicAdapter
            layoutManager = manager
        }

        mViewModel.getSceneryDailyData().observe(this) {
            val list = mutableListOf<SceneryDaily>()
            it.forEach {s ->
                list.add(s!!)
            }

            dailyScenicAdapter.setData(list)
        }
    }

}