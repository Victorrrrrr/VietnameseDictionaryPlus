package com.gp.learn.game

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.cardview.widget.CardView
import com.gp.common.provider.MainServiceProvider
import com.gp.framework.base.BaseMvvmActivity
import com.gp.framework.ext.onClick
import com.gp.learn.show.ShowActivity
import com.gp.mod_learn.databinding.ActivityGameStatusBinding


class GameStatusActivity : BaseMvvmActivity<ActivityGameStatusBinding, GameViewModel>() {

    companion object {
        const val GAME_STATUS = "gameStatus"
        const val STATUS_SUCCESS = 1
        const val STATUS_FAIL = -1
    }

//    private val showActivity: ShowActivity = ShowActivity()



    override fun onBackPressed() {
        MainServiceProvider.toMain(this@GameStatusActivity, 1)
    }

    override fun initView(savedInstanceState: Bundle?) {
        // 禁止夜间模式
        noNight()

        mBinding.cardGameStatus.background.alpha = 150
        mBinding.cardGsReview.getBackground().setAlpha(180)
        mBinding.cardGsExit.getBackground().setAlpha(180)
        mBinding.cardGsAgain.getBackground().setAlpha(180)
        val currentStatus = intent.getIntExtra(GAME_STATUS, 0)
        if (currentStatus == STATUS_FAIL) {
            mBinding.textGsStatus.text = "游戏失败"
        } else {
            mBinding.textGsStatus.text = "游戏胜利"
        }
        mBinding.layoutGsExit.setOnClickListener { onBackPressed() }
        mBinding.layoutGsReview.onClick {
            val intent = Intent()
            intent.setClass(this@GameStatusActivity, ShowActivity::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.putExtra(ShowActivity.SHOW_TYPE, ShowActivity.TYPE_GAME)
            startActivity(intent)
        }
        mBinding.layoutGsAgain.onClick {
            val intent = Intent()
            intent.setClass(this@GameStatusActivity, LoadGameActivity::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }

    }


}