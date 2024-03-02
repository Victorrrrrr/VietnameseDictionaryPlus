package com.gp.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.gp.common.constant.KEY_INDEX
import com.gp.common.constant.MAIN_ACTIVITY_HOME
import com.gp.framework.base.BaseDataBindActivity
import com.gp.main.databinding.ActivityMainBinding
import com.gp.main.ui.home.HomeFragment

@Route(path = MAIN_ACTIVITY_HOME)
class MainActivity : BaseDataBindActivity<ActivityMainBinding>() {

    companion object {
        fun start(context: Context, index: Int = 0) {
            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra(KEY_INDEX, index)
            context.startActivity(intent)
        }
    }


    override fun initView(savedInstanceState: Bundle?) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.container, HomeFragment())
        transaction.commit()
    }
}