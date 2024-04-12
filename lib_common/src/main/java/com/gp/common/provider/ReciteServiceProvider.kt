package com.gp.common.provider

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.launcher.ARouter
import com.gp.common.constant.RECITE_SERVICE_RECITE
import com.gp.common.model.LearnWordBean
import com.gp.common.service.IReciteService

object ReciteServiceProvider {

    @Autowired(name = RECITE_SERVICE_RECITE)
    lateinit var reciteService: IReciteService

    init {
        ARouter.getInstance().inject(this)
    }


    fun toReciteLoad(context: Context, index: Int = 0) {
        reciteService.toReciteLoad(context, index)
    }


    fun toReciteWord(context: Context, list: List<LearnWordBean>) {
        reciteService.toReciteWord(context, list)
    }


}