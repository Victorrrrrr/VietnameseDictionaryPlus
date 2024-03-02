package com.gp.main.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gp.framework.base.BaseMvvmFragment
import com.gp.main.R
import com.gp.main.databinding.FragmentHomeBinding
import com.gp.main.ui.MainViewModel


class HomeFragment : BaseMvvmFragment<FragmentHomeBinding, MainViewModel>() {

    companion object {
        const val GRANT_TYPE = "password"
        const val USERNAME = "wzf"
        const val PASSWORD = "123456"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("1112","onCreate")

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun initView(view: View, savedInstanceState: Bundle?) {
//        mViewModel.getHomeInfoList(0).observe(this) {
//            Log.d("1112", "result : ${it}")
//        }

        mViewModel.sendAuthRequest(
            GRANT_TYPE,
            USERNAME,
            PASSWORD
        ).observe(this) {
            Log.d("1112", "result : ${it}")
        }



    }

}