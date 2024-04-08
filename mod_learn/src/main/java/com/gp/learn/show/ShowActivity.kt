package com.gp.learn.show

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.gp.common.model.LearnWordBean
import com.gp.common.provider.MainServiceProvider
import com.gp.framework.base.BaseMvvmActivity
import com.gp.framework.ext.onClick
import com.gp.learn.data.ItemShow
import com.gp.learn.game.GameActivity
import com.gp.learn.game.GameViewModel
import com.gp.mod_learn.databinding.ActivityShowBinding


class ShowActivity : BaseMvvmActivity<ActivityShowBinding, GameViewModel>() {

    companion object {
        const val SHOW_TYPE = "showType"

        const val TYPE_MATCH = 1

        const val TYPE_SPEED = 2

        const val TYPE_GAME = 3

        const val TAG = "ShowActivity"
    }

    private val showList: MutableList<ItemShow> = ArrayList()

    private var wordList: MutableList<LearnWordBean> = ArrayList()

    private val FINISH = 0

    private var showWordAdapter: ShowWordAdapter? = null

    @SuppressLint("HandlerLeak")
    private val handler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                FINISH -> showWordAdapter?.notifyDataSetChanged()
            }
        }
    }




    override fun initView(savedInstanceState: Bundle?) {
        val linearLayoutManager = LinearLayoutManager(this)
        mBinding.recyclerShow.layoutManager = linearLayoutManager
        mBinding.ivHome.onClick {
            onBackPressed()
        }

        showWordAdapter = ShowWordAdapter()
        mBinding.recyclerShow.adapter = showWordAdapter

        //showProgressDialog();
        Thread {
            searchWord()
            bindData()
            val message = Message()
            message.what = FINISH
            handler.sendMessage(message)
        }.start()
    }


    fun searchWord() {
        wordList.clear()
        val currentType = intent.getIntExtra(SHOW_TYPE, 0)
        Log.d(TAG, "currentType: $currentType")
//        Log.d(TAG, "searchWord: " + MatchActivity.allMatches.size())
        when (currentType) {
            TYPE_MATCH -> {
//                Log.d(TAG, "searchWord: ")
//                for (match in MatchActivity.allMatches) {
//                    val words: List<Word> =
//                        LitePal.where("wordId = ?", match.getId() + "").select("wordId", "word")
//                            .find(
//                                Word::class.java
//                            )
//                    wordList.add(words[0])
//                }
            }

//            TYPE_SPEED -> wordList = SpeedActivity.wordList.clone()
            TYPE_GAME -> {
                for (word in GameActivity.alreadyWords) {
                    wordList.add(word)
                }
                GameActivity.alreadyWords.clear()
            }
        }
    }

    private fun bindData() {
        showList.clear()
        for (word in wordList) {
            showList.add(
                ItemShow(
                word.id,
                word.wordVi,
                "${word.pos}.${word.wordZh} ",
                false
            ))
//            if (word.getIsCollected() === 1) showList.add(
//                ItemShow(
//                    word.getWordId(),
//                    word.getWord(),
//                    stringBuilder.toString(),
//                    true
//                )
//            ) else showList.add(
//                ItemShow(
//                    word.getWordId(),
//                    word.getWord(),
//                    stringBuilder.toString(),
//                    false
//                )
//            )
        }
        showWordAdapter!!.setData(showList)
        showWordAdapter!!.notifyDataSetChanged()
    }

    override fun onBackPressed() {
        MainServiceProvider.toMain(this, 1)
        finish()
    }


}