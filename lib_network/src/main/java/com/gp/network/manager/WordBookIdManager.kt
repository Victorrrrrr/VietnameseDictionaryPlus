package com.gp.network.manager

import com.gp.common.constant.RECITE_NUM
import com.gp.common.constant.WORD_BOOK_COUNT
import com.gp.common.constant.WORD_BOOK_ID
import com.gp.framework.utils.LogUtil
import com.tencent.mmkv.MMKV

object WordBookIdManager {

    /**
     * 保存id
     */
    fun saveWordBookId(id: Int) {
        val mmkv = MMKV.defaultMMKV()
        LogUtil.d(id.toString(), tag = "wordbookId")
        mmkv.encode(WORD_BOOK_ID, id)
    }


    /**
     * 获取id
     * @return token
     */
    fun getWordBookId() : Int {
        val mmkv = MMKV.defaultMMKV()
        return mmkv.decodeInt(WORD_BOOK_ID, -1)
    }

    /**
     * 保存id
     */
    fun saveWordBookNum(count: Int) {
        val mmkv = MMKV.defaultMMKV()
        LogUtil.d(count.toString(), tag = "wordbookCount")
        mmkv.encode(WORD_BOOK_COUNT, count)
    }


    /**
     * 获取id
     * @return token
     */
    fun getWordBookNum() : Int {
        val mmkv = MMKV.defaultMMKV()
        return mmkv.decodeInt(WORD_BOOK_COUNT, 0)
    }



    /**
     * 清除token
     */
    fun clearWordBookId() {
        saveWordBookId(-1)
        saveWordBookNum(0)
    }



    /**
     * 保存id
     */
    fun saveReciteNum(count: Int) {
        val mmkv = MMKV.defaultMMKV()
        LogUtil.d(count.toString(), tag = "wordbookId")
        mmkv.encode(RECITE_NUM, count)
    }


    /**
     * 获取id
     * @return token
     */
    fun getReciteNum() : Int {
        val mmkv = MMKV.defaultMMKV()
        return mmkv.decodeInt(RECITE_NUM, 10)
    }


}