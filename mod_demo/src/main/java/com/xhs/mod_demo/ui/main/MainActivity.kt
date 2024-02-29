package com.xhs.mod_demo.ui.main


import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.gp.framework.base.BaseBindindActivity
import com.gp.framework.utils.LogUtil
import com.xhs.mod_demo.R
import com.xhs.mod_demo.databinding.ActivityMainBinding
import com.xhs.mod_demo.ui.main.fragment.HomeFragment
import com.xhs.mod_demo.ui.main.fragment.LearnFragment
import com.xhs.mod_demo.ui.main.fragment.MeFragment


class MainActivity : BaseBindindActivity<ActivityMainBinding>({
    ActivityMainBinding.inflate(it)
}) {
    companion object {
        private const val TAG = "MainActivity"
    }

    override fun initView(savedInstanceState: Bundle?) {
        binding.navView.setOnItemSelectedListener {
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

    private fun switchFragment(position: Int) = binding.mainViewPager.setCurrentItem(position, true)

    private fun initViewPager() {
        // 不禁止用户滑动Viewpager事件
        binding.mainViewPager.isUserInputEnabled = false
        // viewpager左和右单边预加载的页面的数量
        binding.mainViewPager.offscreenPageLimit = 2
        //
        binding.mainViewPager.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int = 3

            override fun createFragment(position: Int): Fragment = when(position) {
                0 -> HomeFragment()
                1 -> LearnFragment()
                2 -> MeFragment()
                else -> HomeFragment()
            }

        }
    }

}