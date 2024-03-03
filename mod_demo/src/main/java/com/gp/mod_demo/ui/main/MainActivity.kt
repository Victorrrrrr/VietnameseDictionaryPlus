package com.gp.mod_demo.ui.main


import android.os.Bundle
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.gp.framework.base.BaseBindingActivity
import com.gp.framework.utils.LogUtil
import com.gp.mod_demo.R
import com.gp.mod_demo.databinding.ActivityMainBinding
import com.gp.mod_demo.ui.main.fragment.HomeFragment
import com.gp.mod_demo.ui.main.fragment.LearnFragment
import com.gp.mod_demo.ui.main.fragment.MeFragment


class MainActivity : BaseBindingActivity<ActivityMainBinding>({
    ActivityMainBinding.inflate(it)
}) {
    companion object {
        private const val TAG = "MainActivity"
    }

    private var onPageChangeCallback : OnPageChangeCallback? = null
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

    private fun switchFragment(position: Int) = binding.mainViewPager.setCurrentItem(position, false)

    private fun initViewPager() {
        // 是否允许用户滑动Viewpager事件
        binding.mainViewPager.isUserInputEnabled = true
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

        onPageChangeCallback = object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.navView.menu[position].isChecked = true
            }
        }

        binding.mainViewPager.registerOnPageChangeCallback(onPageChangeCallback!!)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.mainViewPager.unregisterOnPageChangeCallback(onPageChangeCallback!!)
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_main
    }

}