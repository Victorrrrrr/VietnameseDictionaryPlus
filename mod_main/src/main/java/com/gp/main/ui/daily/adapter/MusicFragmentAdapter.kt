package com.gp.main.ui.daily.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class MusicFragmentAdapter(fm : FragmentManager, list : MutableList<Fragment>) : FragmentPagerAdapter(fm) {

    private var fList : MutableList<Fragment>

    init {
        fList = list
    }

    override fun getCount() = fList.size

    override fun getItem(position: Int) = fList.get(position)
}