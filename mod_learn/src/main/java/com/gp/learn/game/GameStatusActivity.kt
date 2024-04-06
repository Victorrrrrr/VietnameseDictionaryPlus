package com.gp.learn.game

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.cardview.widget.CardView
import com.gp.common.provider.MainServiceProvider
import com.gp.framework.base.BaseMvvmActivity
import com.gp.mod_learn.databinding.ActivityGameStatusBinding


class GameStatusActivity : BaseMvvmActivity<ActivityGameStatusBinding, GameViewModel>() {

    init {
//        const val GAME_STATUS = "gameStatus"
//        const val STATUS_SUCCESS = 1
//        const val STATUS_FAIL = -1
    }

//    private val showActivity: ShowActivity = ShowActivity()



    override fun onBackPressed() {
        MainServiceProvider.toMain(this@GameStatusActivity, 1)
    }

    override fun initView(savedInstanceState: Bundle?) {
//        noNight()
//        init()
//        cardGameStatus!!.background.alpha = 150
//        cardReview.getBackground().setAlpha(180)
//        cardExit.getBackground().setAlpha(180)
//        cardAgain.getBackground().setAlpha(180)
//        val currentStatus = intent.getIntExtra(GAME_STATUS, 0)
//        if (currentStatus == STATUS_FAIL) {
//            textStatus!!.text = "游戏失败"
//        } else {
//            textStatus!!.text = "游戏胜利"
//        }
//        layoutExit!!.setOnClickListener { onBackPressed() }
//        layoutReview.setOnClickListener(View.OnClickListener {
//            val intent = Intent()
//            intent.setClass(this@GameStatusActivity, ShowActivity::class.java)
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//            intent.putExtra(showActivity.SHOW_TYPE, showActivity.TYPE_GAME)
//            startActivity(intent)
//        })
//        layoutAgain.setOnClickListener(View.OnClickListener {
//            ActivityCollector.startOtherActivity(
//                this@GameStatusActivity,
//                LoadGameActivity::class.java
//            )
//        })

    }


}