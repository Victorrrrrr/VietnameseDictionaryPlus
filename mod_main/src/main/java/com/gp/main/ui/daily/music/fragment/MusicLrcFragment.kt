package com.gp.main.ui.daily.music.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gp.framework.base.BaseMvvmFragment
import com.gp.main.R
import com.gp.main.databinding.FragmentMusicLrcBinding
import com.gp.main.ui.daily.viewmodel.DailyViewModel


class MusicLrcFragment : BaseMvvmFragment<FragmentMusicLrcBinding, DailyViewModel>() {


    override fun initView(view: View, savedInstanceState: Bundle?) {
        arguments?.let {
            mBinding?.mLrcView?.text = it.getString("lyric")
        }

    }


}