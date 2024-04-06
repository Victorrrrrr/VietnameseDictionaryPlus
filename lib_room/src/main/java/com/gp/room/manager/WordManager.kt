package com.gp.room.manager

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gp.common.model.SearchWordBean
import com.gp.room.dao.WordListCacheDao
import com.gp.room.database.VDDatabase
import com.gp.room.entity.PersonInfo
import com.gp.room.entity.WordInfo

/**
 * @Author: gxy
 * @Email: yyguanxiongyao@163.com
 * @Date: 2024/4/6:16:41.
 * @Desrciption: 描述
 */
object WordManager {

//    private val wordDao by lazy { VDDatabase.getInstance().wordListDao() }
    suspend fun getWordList() : SearchWordBean? {
//        return wordDao.queryAll()
        return null
    }


    suspend fun saveWordList(list: MutableList<SearchWordBean>) {

    }
}