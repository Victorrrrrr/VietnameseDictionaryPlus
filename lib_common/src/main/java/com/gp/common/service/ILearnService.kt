package com.gp.common.service

import android.content.Context
import com.alibaba.android.arouter.facade.template.IProvider

interface ILearnService : IProvider {

    fun toGame(context: Context)


    fun toWordMatch(context: Context)


    fun toNote(context: Context)

    fun toVoiceTrans(context: Context)

    fun toQuick(context: Context)
}