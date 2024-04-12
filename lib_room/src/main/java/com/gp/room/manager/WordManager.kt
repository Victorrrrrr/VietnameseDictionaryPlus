package com.gp.room.manager

import com.gp.common.model.SearchWordBean
import com.gp.room.database.VDDatabase
import com.gp.room.entity.WordBeanInfo

/**
 * @Author: gxy
 * @Email: yyguanxiongyao@163.com
 * @Date: 2024/4/6:16:41.
 * @Desrciption: 描述
 */
object WordManager {

    private val wordDao by lazy { VDDatabase.getInstance().wordListDao() }
    suspend fun getWordList() : WordBeanInfo? {
        return wordDao.queryAll()
    }


    suspend fun saveWordList(list: MutableList<SearchWordBean>) {

    }
}