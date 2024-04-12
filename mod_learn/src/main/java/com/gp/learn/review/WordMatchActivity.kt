package com.gp.learn.review

import android.os.Bundle
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.gp.common.constant.LEARN_ACTIVITY_MATCH
import com.gp.common.model.LearnWordBean
import com.gp.common.model.WordBeanItem
import com.gp.framework.base.BaseMvvmActivity
import com.gp.learn.data.ItemMatch
import com.gp.learn.game.GameViewModel
import com.gp.mod_learn.databinding.ActivityWordMatchBinding
import com.gp.network.manager.WordBookIdManager
import java.util.Collections


@Route(path = LEARN_ACTIVITY_MATCH)
class WordMatchActivity : BaseMvvmActivity<ActivityWordMatchBinding, GameViewModel>() {


    companion object {
        @JvmField var wordList: ArrayList<LearnWordBean> = ArrayList()
        @JvmField var matchList: ArrayList<ItemMatch> = ArrayList()
        @JvmField var allMatches: ArrayList<ItemMatch> = ArrayList()
        @JvmField var receiveWordList = ArrayList<LearnWordBean>()
    }

    private var matchAdapter : MatchAdapter? = null

    override fun initView(savedInstanceState: Bundle?) {
        showLoading()
        startLoadWord()
        windowExplode()
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        mBinding.recyclerMt.layoutManager = layoutManager
        matchAdapter = MatchAdapter(matchList)
        mBinding.recyclerMt.adapter = matchAdapter
    }

    private fun startLoadWord() {
        allMatches.clear()
        if (wordList.isNotEmpty()) wordList.clear()
        if (matchList.isNotEmpty()) matchList.clear()
        if (receiveWordList.isNotEmpty()) receiveWordList.clear()
        var words : HashMap<Int, LearnWordBean> = HashMap()
        mViewModel.getWordLearn(WordBookIdManager.getReciteNum()){ wordList ->
            dismissLoading()

            wordList?.forEach {
                words[it.id] = it
                receiveWordList.add(it)
            }
            val idsArray = mutableListOf<Int>()
            words.forEach {
                idsArray.add(it.key)
            }

            for(i in 0 until idsArray.size) {
                matchList.add(
                    ItemMatch(
                        idsArray[i],
                        words[idsArray[i]]!!.wordVi,
                        false,
                        false
                    )
                )

                allMatches.add(
                    ItemMatch(
                        words[idsArray.get(i)]?.id ?: -1,
                        words.get(idsArray[i])!!.wordVi,
                        false,
                        false
                    )
                )

                matchList.add(
                    ItemMatch(
                        words[idsArray.get(i)]?.id ?: -1,
                        words.get(idsArray[i])!!.wordZh,
                        false,
                        false
                    )
                )
            }
            matchList.shuffle()
            matchAdapter?.notifyDataSetChanged()

        }.observe(this) {}


    }


}