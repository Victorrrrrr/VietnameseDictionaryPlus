package com.gp.learn.game

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.gp.common.model.LearnWordBean
import com.gp.common.model.Option
import com.gp.common.provider.MainServiceProvider
import com.gp.framework.base.BaseMvvmActivity
import com.gp.framework.toast.TipsToast
import com.gp.framework.utils.MediaHelper.playLocalFileRepeat
import com.gp.framework.utils.MediaHelper.releasePlayer
import com.gp.mod_learn.R
import com.gp.mod_learn.databinding.ActivityGameBinding
import java.util.Collections


class GameActivity : BaseMvvmActivity<ActivityGameBinding, GameViewModel>() {

    companion object {
        @JvmField var gameWord: MutableList<LearnWordBean> = ArrayList()
    }

    var alreadyWords = ArrayList<Int>()

    private val meanChoiceList: MutableList<Option> = ArrayList()

    private var meanChoiceAdapter: MeanChoiceAdapter? = null

    private var handler1: Handler? = null
    private var handler2: Handler? = null

    private var runnable1: Runnable? = null
    private var runnable2: Runnable? = null

    // 猫的进度
    private var progressCat = 0

    // 老鼠的进度
    private var progressMouse = 800

    // 猫平时增加的进度
    private val addCat = 2

    // 老鼠平时增加的进度
    private val addMouse = 1

    // 回答正确时老鼠增加的进度
    private val addRightMouse = 200

    // 回答错误时猫增加的进度
    private val addWrongCat = 70

    // 没有及时回答时猫增加的进度
    private val addNACat = 30

    // 规定剩余时间
    private var remainTime = 5


    private var inFinish = false

    private var currentWord: LearnWordBean? = null

    // 当前展示的单词下标
    private var currentIndex = 0

    // 所有单词
    // 提供游戏单词
//    var allWord: List<Word> = ArrayList<Word>()
//
    // 游戏里面的单词
    // 从上面的单词中抽取50个
//    var gameWord: MutableList<GameWord> = ArrayList<GameWord>()

    private var progressWidth = 0


    override fun initView(savedInstanceState: Bundle?) {
        TipsToast.showTips(gameWord.toString())
        playLocalFileRepeat(R.raw.game)
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        mBinding.recyclerGmBottom.layoutManager = layoutManager
        meanChoiceAdapter = MeanChoiceAdapter(meanChoiceList)
        mBinding.recyclerGmBottom.adapter = meanChoiceAdapter
        setWordMeanData()
        mBinding.progressGm.progress = progressCat
        mBinding.progressGm.secondaryProgress = progressMouse
        mBinding.progressGm.max = 10000
        mBinding.progressGm.post { progressWidth = mBinding.progressGm.width }
        handler1 = Handler()
        Thread {
            runnable1 = object : Runnable {
                override fun run() {
                    if (!inFinish) {
                        handler1?.postDelayed(this, 10)
                        // 设置猫和老鼠的进度
                        progressCat += addCat
                        progressMouse += addMouse
                        mBinding.progressGm.progress = progressCat
                        mBinding.progressGm.secondaryProgress = progressMouse
                        // 设置头像偏移
                        mBinding.imgGmCat.translationX =
                            progressCat.toFloat() / mBinding.progressGm.max * progressWidth
                        mBinding.imgGmMouse.setTranslationX(progressMouse.toFloat() / mBinding.progressGm.max * progressWidth)
                        // 说明猫已经追赶上老鼠了
                        if (progressMouse <= progressCat) {
                            stopTime1()
                            inFinish = true
                            val intent = Intent(
                                this@GameActivity,
                                GameStatusActivity::class.java
                            )
                            intent.putExtra(
                                GameStatusActivity.GAME_STATUS,
                                GameStatusActivity.STATUS_FAIL
                            )
                            startActivity(intent)
                            finish()
                            // 老鼠已达到顶点
                        } else if (progressMouse >= mBinding.progressGm.max) {
                            stopTime1()
                            inFinish = true
                            val intent = Intent(
                                this@GameActivity,
                                GameStatusActivity::class.java
                            )
                            intent.putExtra(
                                GameStatusActivity.GAME_STATUS,
                                GameStatusActivity.STATUS_SUCCESS
                            )
                            startActivity(intent)
                            finish()
                        }
                    }
                }
            }
            handler1?.postDelayed(runnable1!!, 10)
        }.start()
        meanChoiceAdapter?.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(
                parent: RecyclerView?,
                view: View?,
                position: Int,
                itemWordMeanChoice: Option?
            ) {
                if (MeanChoiceAdapter.isFirstClick) {
                    // 答错了
//                    if (itemWordMeanChoice?.id != currentWord?.id) {
//                        answerWrong()
//                        itemWordMeanChoice.setRight(ItemWordMeanChoice.WRONG)
//                        meanChoiceAdapter?.notifyDataSetChanged()
//                        MeanChoiceAdapter.isFirstClick = false
//                        Handler().postDelayed({
//                            MeanChoiceAdapter.isFirstClick = true
//                            setWordMeanData()
//                        }, 250)
//                    } else {
//                        // 答对了
//                        answerRight()
//                        itemWordMeanChoice.setRight(ItemWordMeanChoice.RIGHT)
//                        meanChoiceAdapter?.notifyDataSetChanged()
//                        MeanChoiceAdapter.isFirstClick = false
//                        Handler().postDelayed({
//                            MeanChoiceAdapter.isFirstClick = true
//                            setWordMeanData()
//                        }, 250)
//                    }
                }
            }
        })
        handler2 = Handler()
        runnable2 = object : Runnable {
            override fun run() {
                mBinding.textGmTime.text = "剩余时间：" + remainTime--
                handler2?.postDelayed(this, 1000)
                if (inFinish) stopTime2()
                if (remainTime == -1) {
                    noAnswer()
                    remainTime = 5
                    setWordMeanData()
                }
            }
        }
        handler2?.postDelayed(runnable2!!, 10)
    }


    private fun setWordMeanData() {
        setRandomWord()
        if (!meanChoiceList.isEmpty()) meanChoiceList.clear()
        alreadyWords.add(currentWord!!.id)
//        meanChoiceList.add(
//            Option(
//                currentWord.getId(),
//                currentWord.getWordMeans(),
//                ItemWordMeanChoice.NOTSTART
//            )
//        )
//        val randomIds: IntArray =
//            NumberController.getRandomExceptList(0, gameWord.size - 1, 3, currentIndex)
//        Log.d(TAG, "currentIndex:$currentIndex")
//        Log.d(TAG, "size:" + gameWord.size)
//        for (i in randomIds.indices) {
//            Log.d(TAG, "otherIndex:" + randomIds[i])
//            meanChoiceList.add(
//                ItemWordMeanChoice(
//                    gameWord[randomIds[i]].getId(),
//                    gameWord[randomIds[i]].getWordMeans(),
//                    ItemWordMeanChoice.NOTSTART
//                )
//            )
//        }
        Collections.shuffle(meanChoiceList)
        meanChoiceAdapter?.notifyDataSetChanged()
    }

    private fun setRandomWord() {
        currentWord = gameWord[currentIndex]
        mBinding.textGmWord.text = currentWord?.wordVi
    }

    // 停止计时器
    private fun stopTime1() {
        handler1?.removeCallbacks(runnable1!!)
    }

    private fun stopTime2() {
        handler2?.removeCallbacks(runnable2!!)
    }

    private fun answerRight() {
        progressMouse =
            if (progressMouse + addRightMouse == mBinding.progressGm.max) mBinding.progressGm.max else progressMouse + addRightMouse
        remainTime = 5
        currentIndex++
    }

    private fun answerWrong() {
        progressCat = progressCat + addWrongCat
        remainTime = 5
        currentIndex++
    }

    private fun noAnswer() {
        progressCat = progressCat + addNACat
        currentIndex++
    }

    override fun onBackPressed() {
        super.onBackPressed()
        releasePlayer()
        MainServiceProvider.toMain(this@GameActivity, index = 1)
        inFinish = true
        stopTime1()
        stopTime2()
        alreadyWords.clear()
    }

    override fun onDestroy() {
        super.onDestroy()
        releasePlayer()
        gameWord.clear()
    }


}