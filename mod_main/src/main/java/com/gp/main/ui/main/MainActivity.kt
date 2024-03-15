package com.gp.main.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.alibaba.android.arouter.facade.annotation.Route
import com.gp.common.constant.KEY_INDEX
import com.gp.common.constant.MAIN_ACTIVITY_HOME
import com.gp.framework.base.BaseDataBindActivity
import com.gp.framework.toast.TipsToast
import com.gp.framework.utils.LogUtil
import com.gp.framework.utils.getStringFromResource
import com.gp.lib_framework.utils.StatusBarSettingHelper
import com.gp.main.R
import com.gp.main.databinding.ActivityMainBinding
import com.gp.main.ui.home.HomeFragment
import com.gp.main.ui.learn.LearnFragment
import com.gp.main.ui.mine.MeFragment

@Route(path = MAIN_ACTIVITY_HOME)
class MainActivity : BaseDataBindActivity<ActivityMainBinding>() {

    companion object {
        fun start(context: Context, index: Int = 0) {
            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra(KEY_INDEX, index)
            context.startActivity(intent)
        }
    }

    private var onPageChangeCallback : ViewPager2.OnPageChangeCallback? = null


    override fun initView(savedInstanceState: Bundle?) {
        StatusBarSettingHelper.setStatusBarTranslucent(this)
        StatusBarSettingHelper.statusBarLightMode(this, true)
        mBinding.navView.isItemActiveIndicatorEnabled = false
        mBinding.navView.setOnItemSelectedListener {
            LogUtil.d(message = "initView : $it", tag = TAG)
            when (it.itemId) {
                R.id.navigation_home -> switchFragment(0)
                R.id.navigation_learn -> switchFragment(1)
                R.id.navigation_me -> switchFragment(2)
            }
            true
        }
        initViewPager()
    }

    private fun switchFragment(position: Int) = mBinding.mainViewPager.setCurrentItem(position, false)

    private fun initViewPager() {
        // 是否允许用户滑动Viewpager事件
        mBinding.mainViewPager.isUserInputEnabled = true
        // viewpager左和右单边预加载的页面的数量
        mBinding.mainViewPager.offscreenPageLimit = 2
        //
        mBinding.mainViewPager.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int = 3

            override fun createFragment(position: Int): Fragment = when(position) {
                0 -> HomeFragment()
                1 -> LearnFragment()
                2 -> MeFragment()
                else -> HomeFragment()
            }
        }

        onPageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                mBinding.navView.menu[position].isChecked = true
            }
        }

        mBinding.mainViewPager.registerOnPageChangeCallback(onPageChangeCallback!!)
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding.mainViewPager.unregisterOnPageChangeCallback(onPageChangeCallback!!)
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_main
    }


    private var lastPressedTime : Long = 0L


    /**
     * 防返回误触，设置二次退出
     */
    override fun onBackPressed() {
        if(System.currentTimeMillis() - lastPressedTime < 2000) {
            super.onBackPressed()
        } else {
            TipsToast.showTips(getStringFromResource(com.gp.lib_widget.R.string.exit_twice_tip))
            lastPressedTime = System.currentTimeMillis()
        }
    }

}