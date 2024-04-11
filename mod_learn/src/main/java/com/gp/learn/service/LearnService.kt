package com.gp.learn.service

import android.content.Context
import android.content.Intent
import com.alibaba.android.arouter.facade.annotation.Route
import com.gp.common.constant.LEARN_SERVICE_LEARN
import com.gp.common.service.ILearnService
import com.gp.learn.game.LoadGameActivity
import com.gp.learn.quick.QuickActivity
import com.gp.learn.review.WordMatchActivity
import com.gp.learn.tool.note.FolderActivity
import com.gp.learn.tool.voice_trans.VoiceTransActivity

@Route(path = LEARN_SERVICE_LEARN)
class LearnService : ILearnService{
    override fun toGame(context: Context) {
        context.startActivity(Intent(context, LoadGameActivity::class.java))
    }

    override fun toWordMatch(context: Context) {
        context.startActivity(Intent(context, WordMatchActivity::class.java))
    }

    override fun toNote(context: Context) {
        context.startActivity(Intent(context, FolderActivity::class.java))
    }

    override fun toVoiceTrans(context: Context) {
        context.startActivity(Intent(context, VoiceTransActivity::class.java))
    }

    override fun toQuick(context: Context) {
        context.startActivity(Intent(context, QuickActivity::class.java))
    }


    override fun init(context: Context?) {

    }
}