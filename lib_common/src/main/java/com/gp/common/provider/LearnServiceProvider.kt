package com.gp.common.provider

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.launcher.ARouter
import com.gp.common.constant.LEARN_SERVICE_LEARN
import com.gp.common.service.ILearnService

object LearnServiceProvider {

    @Autowired(name = LEARN_SERVICE_LEARN)
    lateinit var learnService: ILearnService

    init {
        ARouter.getInstance().inject(this)
    }

    fun toGame(context: Context) {
        learnService.toGame(context)
    }

    fun toNote(context: Context) {
        learnService.toNote(context)
    }

    fun toWordMatch(context: Context) {
        learnService.toWordMatch(context)
    }

    fun toVoiceTrans(context: Context) {
        learnService.toVoiceTrans(context)
    }

    fun toQuickPass(context: Context) {
        learnService.toQuick(context)
    }

}