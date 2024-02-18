package com.gp.main.service

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.gp.common.constant.MAIN_SERVICE_HOME
import com.gp.common.service.IMainService
import com.gp.main.MainActivity

@Route(path = MAIN_SERVICE_HOME)
class MainService : IMainService {
    override fun toMain(context: Context, index: Int) {
        MainActivity.start(context, index)
    }

    override fun init(context: Context?) {

    }
}