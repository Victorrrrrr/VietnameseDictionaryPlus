package com.gp.framework.adapter

import android.util.SparseArray
import androidx.core.util.size
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * @Author: gxy
 * @Email: yyguanxiongyao@163.com
 * @Date: 2024/1/24:01:12.
 * @Desrciption: FragmentStateAdapter
 */
class ViewPage2FragmentAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    var fragments : SparseArray<Fragment>
) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    /**class ViewPage2FragmentAdapter(activity: FragmentActivity, var fragments: SparseArray<Fragment>) :
    FragmentStateAdapter(activity) {*/
    //FragmentStateAdapter内部自己会管理已实例化的fragment对象，所以不需要考虑复用的问题。
    override fun createFragment(i: Int): Fragment {
        return fragments[i]
    }

    override fun getItemCount(): Int {
        return fragments.size
    }

    fun setData(fragments: SparseArray<Fragment>) {
        this.fragments = fragments
    }
}