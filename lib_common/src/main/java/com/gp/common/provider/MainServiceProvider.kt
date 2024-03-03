package com.gp.common.provider

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.launcher.ARouter
import com.gp.common.constant.MAIN_SERVICE_HOME
import com.gp.common.service.IMainService

object MainServiceProvider {

    @Autowired(name = MAIN_SERVICE_HOME)
    lateinit var mainService: IMainService


    init {
        ARouter.getInstance().inject(this)
    }


    /**
     * 跳转主页
     * @param context
     * @param index tab位置
     */
    fun toMain(context: Context, index: Int = 0) {
        mainService.toMain(context, index)
    }


}