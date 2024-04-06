package com.gp.recite.service

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.gp.common.constant.RECITE_SERVICE_RECITE
import com.gp.common.service.IReciteService
import com.gp.recite.LoadWordsActivity

@Route(path = RECITE_SERVICE_RECITE)
class ReciteService : IReciteService {
    override fun toReciteLoad(context: Context, index: Int) {
        LoadWordsActivity.start(context, index)
    }

    override fun init(context: Context?) {

    }


}