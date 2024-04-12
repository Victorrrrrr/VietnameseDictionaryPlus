package com.gp.recite.controller

import com.gp.common.model.LearnWordBean

object WordController {

    @JvmField var wordList : List<LearnWordBean>? = null


    @JvmField var isZhToVie : Boolean = false



    @JvmStatic
    fun saveWordList(wordList : List<LearnWordBean>) {
        this.wordList = wordList
    }

    @JvmStatic
    fun getWordList() : List<LearnWordBean>? {
        return this.wordList
    }

}